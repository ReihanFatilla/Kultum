package com.reift.core.domain.usecase.register

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.User

interface RegisterUseCase {
    fun checkIfEmailTaken(email: String): LiveData<Boolean>
    fun checkIfUsernameTaken(username: String): LiveData<Boolean>
    fun saveUser(user: User)
}