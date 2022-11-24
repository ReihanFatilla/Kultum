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
import com.reift.core.domain.model.Comments
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.repository.HomeRepository

class HomeRepositoryImpl(
    val firebaseDataSource: FirebaseDataSource,
    val localDataSource: LocalDataSource
) : HomeRepository {

    val currentUser = localDataSource.getString(Pref.CURRENT_USER) ?: ""

    override fun getKultumForYou(): LiveData<List<Kultum>> {
        val listKultum = MutableLiveData<List<Kultum>>()
        firebaseDataSource.getReference(Ref.KULTUM).addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = arrayListOf<Kultum>()
                    for (i in snapshot.children) {
                        val kultum = i.getValue(Kultum::class.java) ?: return
                        list.add(kultum)
                    }
                    listKultum.value = list
                }

                override fun onCancelled(error: DatabaseError) {

                }

            }
        )
        return listKultum
    }

    override fun addHelpfulKultum(kultum: Kultum) {
        firebaseDataSource.getReference(Ref.KULTUM)
            .child(kultum.urlKey)
            .child(Ref.HELPFUL)
            .child(currentUser)
            .setValue(currentUser)
    }

    override fun removeHelpfulKultum(kultum: Kultum) {
        firebaseDataSource.getReference(Ref.KULTUM)
            .child(kultum.urlKey)
            .child(Ref.HELPFUL)
            .child(currentUser)
            .removeValue()
    }

    override fun addComment(comment: Comments, urlKultum: String) {
        firebaseDataSource.getReference(Ref.KULTUM)
            .child(urlKultum)
            .child(Ref.COMMENTS)
            .child(comment.creator)
            .setValue(comment)
    }

}