package com.reift.core.utils

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.reift.core.constant.Ref
import com.reift.core.data.source.remote.FirebaseDataSource
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.model.User

class UsernameChangeHelper(
    val newUsername: String,
    val currentUser: String,
    val firebaseDataSource: FirebaseDataSource,
) {
    fun changeFollowOnNewUsername() {
        firebaseDataSource.getReference(Ref.USER)
            .addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (i in snapshot.children) {
                            val user = i.getValue(User::class.java)
                            user?.followers?.forEach {
                                if (it.username != currentUser) return
                                val followersRef = firebaseDataSource.getReference(Ref.USER)
                                    .child(user.username)
                                    .child(Ref.FOLLOWERS)
                                followersRef.child(currentUser).removeValue()
                                followersRef.child(newUsername).setValue(newUsername)
                            }
                            user?.follwings?.forEach {
                                if (it.username != currentUser) return
                                val followingsRef = firebaseDataSource.getReference(Ref.USER)
                                    .child(user.username)
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

    fun changeKultumCreator() {
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
}