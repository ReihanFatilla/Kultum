package com.reift.core.domain.usecase.login

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.User

interface LoginUseCase {
    fun checkIfLoginValid(email: String, password: String): LiveData<Boolean>
}