package com.reift.kultum.presentation.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.reift.core.domain.model.User
import com.reift.core.domain.usecase.register.RegisterUseCase

class RegisterViewModel(
    val registerUseCase: RegisterUseCase
): ViewModel() {

    fun checkIfEmailTaken(email: String): LiveData<Boolean> {
        Log.i("Keluarataumasuk", "Ini Di View Model: ${registerUseCase.checkIfEmailTaken(email)}")
        return registerUseCase.checkIfEmailTaken(email)
    }

    fun checkIfUsernameTaken(username: String): LiveData<Boolean> {
        return registerUseCase.checkIfUsernameTaken(username)
    }

    fun saveUser(user: User){
        registerUseCase.saveUser(user)
    }

}