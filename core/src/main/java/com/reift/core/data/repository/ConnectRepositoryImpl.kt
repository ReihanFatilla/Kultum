package com.reift.core.data.repository

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.model.User
import com.reift.core.domain.repository.ConnectRepository

class ConnectRepositoryImpl: ConnectRepository {
    override fun getUserByUsername(username: String): LiveData<User> {
        TODO("Not yet implemented")
    }

    override fun followUser(user: User) {
        TODO("Not yet implemented")
    }

    override fun unfollowUser(user: User) {
        TODO("Not yet implemented")
    }

    override fun getPostedKultum(username: String): LiveData<List<Kultum>> {
        TODO("Not yet implemented")
    }

    override fun getHelpfulKultum(username: String): LiveData<List<Kultum>> {
        TODO("Not yet implemented")
    }
}