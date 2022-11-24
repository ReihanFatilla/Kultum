package com.reift.core.domain.usecase.connect

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.model.User
import com.reift.core.domain.repository.ConnectRepository

class ConnectInteractor(
    val connectRepository: ConnectRepository
): ConnectUseCase {
    override fun getUserByUsername(username: String): LiveData<User> {
        return connectRepository.getUserByUsername(username)
    }

    override fun followUser(user: User) {
        connectRepository.followUser(user)
    }

    override fun unfollowUser(user: User) {
        connectRepository.followUser(user)
    }

    override fun getPostedKultum(username: String): LiveData<List<Kultum>> {
        return connectRepository.getPostedKultum(username)
    }

    override fun getHelpfulKultum(username: String): LiveData<List<Kultum>> {
        return connectRepository.getHelpfulKultum(username)
    }
}