package com.reift.core.domain.repository

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.model.User

interface ProfileRepository {
    fun getUserDetail(): LiveData<User>
    fun getPostedKultum(): LiveData<List<Kultum>>
    fun getHelpfulKultum(): LiveData<List<Kultum>>
}