package com.reift.core.domain.usecase.login

import androidx.lifecycle.LiveData

class LoginInteractor: LoginUseCase {
    override fun checkIfLoginValid(email: String, password: String): LiveData<Boolean> {
        TODO("Not yet implemented")
    }
}