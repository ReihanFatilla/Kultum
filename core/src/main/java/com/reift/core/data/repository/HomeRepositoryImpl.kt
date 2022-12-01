package com.reift.core.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.reift.core.constant.Pref
import com.reift.core.constant.Ref
import com.reift.core.data.source.local.LocalDataSource
import com.reift.core.data.source.remote.FirebaseDataSource
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.repository.HomeRepository

class HomeRepositoryImpl(
    val firebaseDataSource: FirebaseDataSource,
    val localDataSource: LocalDataSource
) : HomeRepository {

    val currentUser = localDataSource.getString(Pref.CURRENT_USER) ?: ""

    override fun getKultumForYou(): LiveData<List<Kultum>> {
        val listKultum = MutableLiveData<List<Kultum>>()

        val kultumRef = firebaseDataSource.getReference(Ref.KULTUM)

        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = arrayListOf<Kultum>()
                for (i in snapshot.children) {
                    val kultum = i.getValue(Kultum::class.java) ?: return
                    list.add(kultum)
                }
                listKultum.value = list.shuffled()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }

        kultumRef.addListenerForSingleValueEvent(valueEventListener)

        return listKultum
    }

    override fun getKultumDetail(urlKultum: String): LiveData<Kultum> {
        val kultum = MutableLiveData<Kultum>()
        firebaseDataSource.getReference(Ref.KULTUM)
            .child(urlKultum)
            .addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        kultum.value = snapshot.getValue(Kultum::class.java)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                }
            )
        return kultum
    }

    override fun isKultumHelpfuled(urlKultum: String): LiveData<Boolean> {
        val isHelpfuled = MutableLiveData<Boolean>()
        firebaseDataSource.getReference(Ref.KULTUM)
            .child(urlKultum)
            .child(Ref.HELPFUL)
            .addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (i in snapshot.children) {
                            val userHelpful = i.getValue<String>()
                            if (userHelpful == currentUser) isHelpfuled.value = true
                        }
                        if (isHelpfuled.value != true) {
                            isHelpfuled.value = false
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                }
            )
        return isHelpfuled
    }

    override fun addHelpfulKultum(urlKultum: String) {
        firebaseDataSource.getReference(Ref.KULTUM)
            .child(urlKultum)
            .child(Ref.HELPFUL)
            .child(currentUser)
            .setValue(currentUser)
    }

    override fun removeHelpfulKultum(urlKultum: String) {
        firebaseDataSource.getReference(Ref.KULTUM)
            .child(urlKultum)
            .child(Ref.HELPFUL)
            .child(currentUser)
            .removeValue()
    }

}