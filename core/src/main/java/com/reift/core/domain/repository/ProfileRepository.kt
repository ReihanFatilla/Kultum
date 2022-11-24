package com.reift.core.domain.repository

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.model.User

interface ProfileRepository {
    fun getUserDetail(username: String): LiveData<User>
    fun getPostedKultum(username: String): LiveData<List<Kultum>>
    fun getHelpfulKultum(username: String): LiveData<List<Kultum>>
}