package com.reift.core.domain.repository

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.User

interface FollowRepository {
    fun getFollowers(username: String): LiveData<List<User>>
    fun getFollowings(username: String): LiveData<List<User>>
}