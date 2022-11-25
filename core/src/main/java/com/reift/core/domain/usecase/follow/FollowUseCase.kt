package com.reift.core.domain.usecase.follow

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.Follow
import com.reift.core.domain.model.User

interface FollowUseCase {
    fun getFollowers(username: String): LiveData<List<Follow>>
    fun getFollowings(username: String): LiveData<List<Follow>>
}