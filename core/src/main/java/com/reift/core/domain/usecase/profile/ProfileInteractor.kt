package com.reift.core.domain.usecase.profile

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.model.User

class ProfileInteractor: ProfileUseCase {
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