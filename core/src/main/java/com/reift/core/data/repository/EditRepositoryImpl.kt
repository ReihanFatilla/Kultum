package com.reift.core.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.reift.core.constant.Pref
import com.reift.core.constant.Ref
import com.reift.core.data.source.local.LocalDataSource
import com.reift.core.data.source.remote.FirebaseDataSource
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.model.User
import com.reift.core.domain.repository.EditRepository

class EditRepositoryImpl(
    val firebaseDataSource: FirebaseDataSource,
    val localDataSource: LocalDataSource
) : EditRepository {

    val currentUser = localDataSource.getString(Pref.CURRENT_USER) ?: ""

    override fun saveEditedProfile(
        username: String?,
        name: String?,
        bio: String?,
        photoUrl: String?
    ) {
        val userRef = firebaseDataSource.getReference(Ref.USER).child(currentUser)
        if (username != null) {
            if (checkIfUserTaken(username).value == true) return
            userRef.setValue(changeNewUsername(userRef, username))
            changeKultumCreator(username)
            changeFollowOnNewUsername(username)
        }
    }

    private fun changeFollowOnNewUsername(newUsername: String) {
        firebaseDataSource.getReference(Ref.USER)
            .addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (i in snapshot.children) {
                            val user = i.getValue(User::class.java)
                            user?.followers?.forEach {
                                if (it.username != currentUser) return
                                val followersRef = firebaseDataSource.getReference(Ref.USER)
                                    .child(user.usernname)
                                    .child(Ref.FOLLOWERS)
                                followersRef.child(currentUser).removeValue()
                                followersRef.child(newUsername).setValue(newUsername)
                            }
                            user?.follwings?.forEach {
                                if (it.username != currentUser) return
                                val followingsRef = firebaseDataSource.getReference(Ref.USER)
                                    .child(user.usernname)
                                    .child(Ref.FOLLOWINGS)
                                followingsRef.child(currentUser).removeValue()
                                followingsRef.child(newUsername).setValue(newUsername)
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                }
            )
    }

    private fun changeKultumCreator(newUsername: String) {
        firebaseDataSource.getReference(Ref.KULTUM)
            .addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (i in snapshot.children) {
                            val kultum = snapshot.getValue(Kultum::class.java)
                            if (kultum?.creator != currentUser) continue
                            firebaseDataSource.getReference(Ref.KULTUM)
                                .child(kultum.urlKey)
                                .child(Ref.CREATOR)
                                .setValue(newUsername)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                }
            )
    }

    override fun checkIfUserTaken(username: String): LiveData<Boolean> {
        val isTaken = MutableLiveData(true)
        firebaseDataSource.getReference(Ref.USER)
            .addValueEventListener(

                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (i in snapshot.children) {
                            if (i.getValue<String>() == username) continue
                            isTaken.value = false
                            break
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                }
            )
        return isTaken
    }

    private fun changeNewUsername(userRef: DatabaseReference, newUsername: String): User? {
        var username: User? = null
        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                if (username != null && user != null) {
                    with(user) {
                        username = User(
                            this.name,
                            newUsername,
                            email,
                            this.photoUrl,
                            this.bio,
                            password,
                            kultum,
                            followers,
                            follwings
                        )
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
        return username
    }

}