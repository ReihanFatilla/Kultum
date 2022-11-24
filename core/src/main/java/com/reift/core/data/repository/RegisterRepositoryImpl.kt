package com.reift.core.data.repository

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.User
import com.reift.core.domain.repository.RegisterRepository

class RegisterRepositoryImpl(

): RegisterRepository {
    override fun checkIfEmailTaken(email: String): LiveData<Boolean> {
        TODO("Not yet implemented")
    }

    override fun saveUser(user: User) {
        TODO("Not yet implemented")
    }
}