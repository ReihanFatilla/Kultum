package com.reift.core.data.repository

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.model.User
import com.reift.core.domain.repository.ProfileRepository

class ProfileRepositoryImpl(

): ProfileRepository {
    override fun getUserDetail(username: String): LiveData<User> {
        TODO("Not yet implemented")
    }

    override fun getPostedKultum(username: String): LiveData<List<Kultum>> {
        TODO("Not yet implemented")
    }

    override fun getHelpfulKultum(username: String): LiveData<List<Kultum>> {
        TODO("Not yet implemented")
    }

}