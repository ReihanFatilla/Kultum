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
import com.reift.core.utils.UsernameChangeHelper

class EditRepositoryImpl(
    val firebaseDataSource: FirebaseDataSource,
    val localDataSource: LocalDataSource
) : EditRepository {

    val currentUser = localDataSource.getString(Pref.CURRENT_USER) ?: ""

    override fun editUsername(username: String) {
//        val userRef = firebaseDataSource.getReference(Ref.USER).child(currentUser)
//        if (checkIfUserTaken(username).value == true) return
//        userRef.setValue(changeNewUsername(userRef, username))
//        val changeHelper = UsernameChangeHelper(username, currentUser, firebaseDataSource)
//        changeHelper.changeKultumCreator()
//        changeHelper.changeFollowOnNewUsername()
    }

    override fun editName(name: String) {
        firebaseDataSource.getReference(Ref.USER)
            .child(currentUser)
            .child(Ref.NAME)
            .setValue(name)
    }

    override fun editBio(bio: String) {
        firebaseDataSource.getReference(Ref.USER)
            .child(currentUser)
            .child(Ref.BIO)
            .setValue(bio)
    }

    override fun editPhotoUrl(photoUrl: String) {
        firebaseDataSource.getReference(Ref.USER)
            .child(currentUser)
            .child(Ref.PHOTO_URL)
            .setValue(photoUrl)

        localDataSource.add(Pref.CURRENT_PHOTO, photoUrl)
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

    override fun getCurrentUser(): LiveData<User> {
        val user = MutableLiveData<User>()
        firebaseDataSource.getReference(Ref.USER)
            .child(currentUser)
            .addValueEventListener(
                object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        user.value = snapshot.getValue(User::class.java)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                }
            )
        return user
    }

    override fun logout() {
        localDataSource.clear()
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
                            kultumAmount,
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