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
import com.reift.core.domain.repository.LoginRepository

class LoginRepositoryImpl(
    val firebaseDataSource: FirebaseDataSource,
    val localDataSource: LocalDataSource
): LoginRepository {
    override fun checkIfLoginValid(email: String, password: String): LiveData<Boolean> {
        val isLoginValid = MutableLiveData<Boolean>()
        firebaseDataSource.getReference(Ref.USER).addValueEventListener(
            object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(i in snapshot.children){
                        val user = i.getValue(User::class.java)
                        if(user?.email != email) continue
                        if(user.password != password) continue
                        isLoginValid.value = true
                        localDataSource.add(Pref.CURRENT_USER, user.username)
                        localDataSource.add(Pref.CURRENT_PHOTO, user.photoUrl)
                        localDataSource.add(Pref.IS_USER_LOGIN, true)
                    }
                    if(isLoginValid.value != true){
                        isLoginValid.value = false
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
        )
        return isLoginValid
    }
}