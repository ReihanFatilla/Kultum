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
): RegisterRepository {

    override fun checkIfEmailTaken(email: String): LiveData<Boolean> {
        var isTaken = MutableLiveData(true)
        firebaseDataSource.getReference(Ref.USER).addValueEventListener(
            object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(i in snapshot.children){
                        val user = i.getValue(User::class.java)
                        if(user?.email != email) {
                            isTaken.value = false
                            Log.i("Keluarataumasuk", " 1 = $isTaken")
                            break
                        } else {
                            Log.i("Keluarataumasuk", " 2 = $isTaken")
                        }
                        Log.i("Keluarataumasuk", " 3 = $isTaken")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
        )
        Log.i("Keluarataumasuk", "4 = $isTaken")
        return isTaken
    }

    override fun checkIfUsernameTaken(username: String): LiveData<Boolean> {
        val isTaken = MutableLiveData(true)
        firebaseDataSource.getReference(Ref.USER).addValueEventListener(
            object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(i in snapshot.children){
                        val user = i.getValue(User::class.java)
                        if(user?.usernname == username) continue
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