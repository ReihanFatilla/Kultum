package com.reift.core.domain.usecase.connect

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.model.User

interface ConnectUseCase {
    fun getUserByUsername(username: String): LiveData<User>
    fun followUser(user: User)
    fun unfollowUser(user: User)
    fun getPostedKultum(username: String): LiveData<List<Kultum>>
    fun getHelpfulKultum(username: String): LiveData<List<Kultum>>
}