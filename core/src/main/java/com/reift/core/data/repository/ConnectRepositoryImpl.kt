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
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.model.User
import com.reift.core.domain.repository.ConnectRepository

class ConnectRepositoryImpl(
    val firebaseDataSource: FirebaseDataSource,
    val localDataSource: LocalDataSource
): ConnectRepository {

    val currentUser = localDataSource.getString(Pref.CURRENT_USER) ?: ""

    override fun getUserByUsername(username: String): LiveData<User> {
        val user = MutableLiveData<User>()
        firebaseDataSource.getReference(Ref.USER)
            .child(username)
            .addValueEventListener(
                object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.exists()){
                            user.value = snapshot.getValue(User::class.java)
                        }

                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                }
            )
        return user
    }

    override fun followUser(username: String) {
        firebaseDataSource.getReference(Ref.USER)
            .child(username)
            .child(Ref.FOLLOWERS)
            .child(currentUser)
            .setValue(currentUser)
    }

    override fun unfollowUser(username: String) {
        firebaseDataSource.getReference(Ref.USER)
            .child(username)
            .child(Ref.FOLLOWERS)
            .child(currentUser)
            .removeValue()
    }

    override fun getPostedKultum(username: String): LiveData<List<Kultum>> {
        val listKultum = MutableLiveData<List<Kultum>>()
        firebaseDataSource.getReference(Ref.KULTUM)
            .addValueEventListener(
                object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val list = arrayListOf<Kultum>()
                        for(i in snapshot.children){
                            val kultum = i.getValue(Kultum::class.java)
                            if(kultum?.creator != username) continue
                            list.add(kultum)
                        }
                        listKultum.value = list
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                }
            )
        return listKultum
    }

    override fun getHelpfulKultum(username: String): LiveData<List<Kultum>> {
        val listKultum = MutableLiveData<List<Kultum>>()
        firebaseDataSource.getReference(Ref.KULTUM)
            .child(Ref.HELPFUL)
            .addValueEventListener(
                object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val list = arrayListOf<Kultum>()
                        for(i in snapshot.children){
                            val kultum = i.getValue(Kultum::class.java)
                            kultum?.helpful?.forEach {
                                if(it.value != username) return
                                list.add(kultum)
                            }
                        }
                        listKultum.value = list
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                }
            )
        return listKultum
    }
}