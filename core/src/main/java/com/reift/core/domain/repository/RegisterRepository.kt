package com.reift.core.domain.repository

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.User

interface RegisterRepository {
    fun checkIfEmailTaken(email: String): LiveData<Boolean>
    fun checkIfUsernameTaken(username: String): LiveData<Boolean>
    fun saveUser(user: User)
}