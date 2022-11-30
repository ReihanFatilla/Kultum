package com.reift.core.domain.usecase.connect

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.model.User

interface ConnectUseCase {
    fun getUserByUsername(username: String? = null): LiveData<List<User>>
    fun followUser(username: String)
    fun unfollowUser(username: String)
    fun getPostedKultum(username: String): LiveData<List<Kultum>>
    fun getHelpfulKultum(username: String): LiveData<List<Kultum>>
}