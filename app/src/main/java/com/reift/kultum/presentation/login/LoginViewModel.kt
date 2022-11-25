package com.reift.kultum.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.reift.core.domain.usecase.login.LoginUseCase

class LoginViewModel(
    val loginUseCase: LoginUseCase
): ViewModel() {
    fun checkIfLoginValid(email: String, password: String): LiveData<Boolean>{
        return loginUseCase.checkIfLoginValid(email, password)
    }
}