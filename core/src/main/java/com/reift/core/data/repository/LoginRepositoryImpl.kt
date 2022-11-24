package com.reift.core.data.repository

import androidx.lifecycle.LiveData
import com.reift.core.domain.repository.LoginRepository

class LoginRepositoryImpl(

): LoginRepository {
    override fun checkIfLoginValid(email: String, password: String): LiveData<Boolean> {
        TODO("Not yet implemented")
    }

}