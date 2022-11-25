package com.reift.core.domain.usecase.follow

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.Follow
import com.reift.core.domain.model.User
import com.reift.core.domain.repository.FollowRepository

class FollowInteractor(
    val followRepository: FollowRepository
): FollowUseCase {
    override fun getFollowers(username: String): LiveData<List<Follow>> {
        return followRepository.getFollowers(username)
    }

    override fun getFollowings(username: String): LiveData<List<Follow>> {
        return followRepository.getFollowers(username)
    }
}