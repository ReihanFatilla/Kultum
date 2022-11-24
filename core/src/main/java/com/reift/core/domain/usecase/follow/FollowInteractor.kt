package com.reift.core.domain.usecase.follow

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.User

class FollowInteractor: FollowUseCase {
    override fun getFollowers(username: String): LiveData<List<User>> {
        TODO("Not yet implemented")
    }

    override fun getFollowings(username: String): LiveData<List<User>> {
        TODO("Not yet implemented")
    }
}