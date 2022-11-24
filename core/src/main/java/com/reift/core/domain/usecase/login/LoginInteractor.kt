package com.reift.core.domain.usecase.login

import androidx.lifecycle.LiveData
import com.reift.core.domain.repository.LoginRepository

class LoginInteractor(
    val loginRepository: LoginRepository
): LoginUseCase {
    override fun checkIfLoginValid(email: String, password: String): LiveData<Boolean> {
        return loginRepository.checkIfLoginValid(email, password)
    }
}