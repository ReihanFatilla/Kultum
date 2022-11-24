package com.reift.core.domain.repository

import androidx.lifecycle.LiveData

interface LoginRepository {
    fun checkIfLoginValid(email: String, password: String): LiveData<Boolean>
}