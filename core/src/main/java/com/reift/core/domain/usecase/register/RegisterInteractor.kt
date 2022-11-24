package com.reift.core.domain.usecase.register

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.User

class RegisterInteractor: RegisterUseCase {
    override fun checkIfEmailTaken(email: String): LiveData<Boolean> {
        TODO("Not yet implemented")
    }

    override fun saveUser(user: User) {
        TODO("Not yet implemented")
    }
}