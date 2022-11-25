package com.reift.core.data.repository

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
): RegisterRepository {

    override fun checkIfEmailTaken(email: String): LiveData<Boolean> {
        val isTaken = MutableLiveData<Boolean>()
        firebaseDataSource.getReference(Ref.USER).addValueEventListener(
            object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(i in snapshot.children){
                        val user = i.getValue(User::class.java)
                        if(user?.email == email) continue
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

    override fun saveUser(user: User) {
        firebaseDataSource.getReference(Ref.USER)
            .child(user.usernname)
            .setValue(user)

        localDataSource.add(Pref.CURRENT_USER, user.usernname)
        localDataSource.add(Pref.IS_USER_LOGIN, true)
    }
}