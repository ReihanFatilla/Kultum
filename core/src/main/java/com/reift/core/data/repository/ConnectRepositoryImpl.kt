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
import com.reift.core.domain.repository.ConnectRepository

class ConnectRepositoryImpl(
    val firebaseDataSource: FirebaseDataSource,
    val localDataSource: LocalDataSource
) : ConnectRepository {

    val currentUser = localDataSource.getString(Pref.CURRENT_USER) ?: ""

    override fun getUserByUsername(username: String?): LiveData<List<User>> {
        val listUser = MutableLiveData<List<User>>()
        firebaseDataSource.getReference(Ref.USER)
            .addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val list = arrayListOf<User>()
                        if (username.isNullOrEmpty()) {
                            for (i in snapshot.children) {
                                val user = i.getValue(User::class.java) ?: continue
                                list.add(user)
                            }
                            listUser.value = list
                        } else {
                            for (i in snapshot.children) {
                                val user = i.getValue(User::class.java) ?: continue
                                if(user.username.contains(username) || user.name.contains(username)) {
                                    list.add(user)
                                }
                            }
                            listUser.value = list
                        }

                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                }
            )
        return listUser
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
            .addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val list = arrayListOf<Kultum>()
                        for (i in snapshot.children) {
                            val kultum = i.getValue(Kultum::class.java)
                            if (kultum?.creator != username) continue
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
            .addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val list = arrayListOf<Kultum>()
                        for (i in snapshot.children) {
                            val kultum = i.getValue(Kultum::class.java)
                            kultum?.helpful?.forEach {
                                if (it.value != username) return
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