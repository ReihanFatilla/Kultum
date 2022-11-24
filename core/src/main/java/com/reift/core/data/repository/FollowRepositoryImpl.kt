package com.reift.core.data.repository

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.User
import com.reift.core.domain.repository.FollowRepository

class FollowRepositoryImpl(

): FollowRepository {
    override fun getFollowers(username: String): LiveData<List<User>> {
        TODO("Not yet implemented")
    }

    override fun getFollowings(username: String): LiveData<List<User>> {
        TODO("Not yet implemented")
    }

}