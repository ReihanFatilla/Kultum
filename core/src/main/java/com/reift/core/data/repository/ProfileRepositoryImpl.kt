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
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.model.User
import com.reift.core.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    val firebaseDataSource: FirebaseDataSource,
    val localDataSource: LocalDataSource
): ProfileRepository {

    val currentUser = localDataSource.getString(Pref.CURRENT_USER) ?: ""

    override fun getUserDetail(): LiveData<User> {
        val user = MutableLiveData<User>()
        firebaseDataSource.getReference(Ref.USER)
            .child(currentUser)
            .addValueEventListener(
                object : ValueEventListener {
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

    override fun getPostedKultum(): LiveData<List<Kultum>> {
        val listKultum = MutableLiveData<List<Kultum>>()
        firebaseDataSource.getReference(Ref.KULTUM)
            .addValueEventListener(
                object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val list = arrayListOf<Kultum>()
                        for(i in snapshot.children){
                            val kultum = i.getValue(Kultum::class.java)
                            if(kultum?.creator != currentUser) return
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

    override fun getHelpfulKultum(): LiveData<List<Kultum>> {
        val listKultum = MutableLiveData<List<Kultum>>()
        firebaseDataSource.getReference(Ref.KULTUM)
            .addValueEventListener(
                object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val list = arrayListOf<Kultum>()
                        for(i in snapshot.children){
                            val kultum = i.getValue(Kultum::class.java)
                            kultum?.helpful?.forEach {
                                if(it.value != currentUser) return
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