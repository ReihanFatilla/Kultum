package com.reift.core.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.reift.core.constant.Pref
import com.reift.core.constant.Ref
import com.reift.core.data.source.local.LocalDataSource
import com.reift.core.data.source.remote.FirebaseDataSource
import com.reift.core.domain.model.User
import com.reift.core.domain.repository.RegisterRepository

class RegisterRepositoryImpl(
    val firebaseDataSource: FirebaseDataSource,
    val localDataSource: LocalDataSource
) : RegisterRepository {

    override fun checkIfEmailTaken(email: String): LiveData<Boolean> {
        val isTaken = MutableLiveData<Boolean>()
        firebaseDataSource.getReference(Ref.USER).addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val result = arrayListOf<Boolean>()
                    for (i in snapshot.children) {
                        val user = i.getValue(User::class.java)
                        if (user?.email == email) result.add(true) else result.add(false)
                        isTaken.value = false
                    }
                    isTaken.value = result.contains(true)
                }

                override fun onCancelled(error: DatabaseError) {
                }

            }
        )
        return isTaken
    }

    override fun checkIfUsernameTaken(username: String): LiveData<Boolean> {
        val isTaken = MutableLiveData<Boolean>()
        firebaseDataSource.getReference(Ref.USER)
            .addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val result = arrayListOf<Boolean>()
                    for (i in snapshot.children) {
                        val user = i.getValue(User::class.java)
                        if (user?.username == username) result.add(true) else result.add(false)
                    }
                    isTaken.value = result.contains(true)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
        )
        return isTaken
    }

    override fun saveUser(user: User) {
        firebaseDataSource.getReference(Ref.USER)
            .child(user.username)
            .setValue(user)

        localDataSource.add(Pref.CURRENT_USER, user.username)
        localDataSource.add(Pref.IS_USER_LOGIN, true)
    }
}