package com.reift.core.data.repository

import com.reift.core.data.source.local.LocalDataSource
import com.reift.core.data.source.remote.FirebaseDataSource
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.repository.PostRepository

class PostRepositoryImpl(
    val firebaseDataSource: FirebaseDataSource,
    val localDataSource: LocalDataSource
): PostRepository {
    override fun postKultum(kultum: Kultum) {
        TODO("Not yet implemented")
    }
}