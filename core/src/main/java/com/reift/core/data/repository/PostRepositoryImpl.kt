package com.reift.core.data.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.childEvents
import com.google.firebase.database.ktx.snapshots
import com.reift.core.constant.Pref
import com.reift.core.constant.Ref
import com.reift.core.data.source.local.LocalDataSource
import com.reift.core.data.source.remote.FirebaseDataSource
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.repository.PostRepository


class PostRepositoryImpl(
    val firebaseDataSource: FirebaseDataSource,
    val localDataSource: LocalDataSource
) : PostRepository {

    val currentUser = localDataSource.getString(Pref.CURRENT_USER) ?: ""

    override fun postKultum(kultum: Kultum) {
        kultum.creator = currentUser

        firebaseDataSource.getReference(Ref.KULTUM)
            .child(kultum.urlKey)
            .setValue(kultum)
    }

}