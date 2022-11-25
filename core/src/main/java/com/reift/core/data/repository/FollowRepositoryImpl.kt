package com.reift.core.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.reift.core.constant.Ref
import com.reift.core.data.source.remote.FirebaseDataSource
import com.reift.core.domain.model.Follow
import com.reift.core.domain.model.User
import com.reift.core.domain.repository.FollowRepository

class FollowRepositoryImpl(
    val firebaseDataSource: FirebaseDataSource
): FollowRepository {
    override fun getFollowers(username: String): LiveData<List<Follow>> {
        val listFollowers = MutableLiveData<List<Follow>>()
        firebaseDataSource.getReference(Ref.USER)
            .child(username)
            .child(Ref.FOLLOWERS)
            .addValueEventListener(
                object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val list = arrayListOf<Follow>()
                        for(i in snapshot.children){
                            val follow = i.getValue(Follow::class.java) ?: continue
                            list.add(follow)
                        }
                        listFollowers.value = list
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                }
            )
        return  listFollowers
    }

    override fun getFollowings(username: String): LiveData<List<Follow>> {
        val listFollowings = MutableLiveData<List<Follow>>()
        firebaseDataSource.getReference(Ref.USER)
            .child(username)
            .child(Ref.FOLLOWINGS)
            .addValueEventListener(
                object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val list = arrayListOf<Follow>()
                        for(i in snapshot.children){
                            val follow = i.getValue(Follow::class.java) ?: continue
                            list.add(follow)
                        }
                        listFollowings.value = list
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                }
            )
        return  listFollowings
    }

}