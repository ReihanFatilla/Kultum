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
import com.reift.core.domain.repository.CommentRepository

class CommentRepositoryImpl(
    val firebaseDataSource: FirebaseDataSource,
    val localDataSource: LocalDataSource
): CommentRepository {

    val currentUser = localDataSource.getString(Pref.CURRENT_USER).orEmpty()
    val currentPhoto = localDataSource.getString(Pref.CURRENT_PHOTO).orEmpty()

    override fun getKultumComments(urlKultum: String): LiveData<List<Comments>> {
        val listKultum = MutableLiveData<List<Comments>>()
        firebaseDataSource.getReference(Ref.KULTUM)
            .child(urlKultum)
            .child(Ref.COMMENTS)
            .addValueEventListener(
                object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val list = arrayListOf<Comments>()
                        for(i in snapshot.children){
                            val comment = i.getValue(Comments::class.java)
                            comment?.let { list.add(it) }
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

    override fun addComment(message: String, urlKultum: String) {

        val comment = Comments(
            currentUser,
            currentPhoto,
            message
        )

        firebaseDataSource.getReference(Ref.KULTUM)
            .child(urlKultum)
            .child(Ref.COMMENTS)
            .child(currentUser)
            .setValue(comment)
    }
}