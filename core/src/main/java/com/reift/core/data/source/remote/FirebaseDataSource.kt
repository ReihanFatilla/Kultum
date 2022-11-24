package com.reift.core.data.source.remote

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseDataSource(
    val firebaseDatabase: FirebaseDatabase
) {
    fun getReference(ref: String): DatabaseReference {
        return firebaseDatabase.getReference(ref)
    }
}