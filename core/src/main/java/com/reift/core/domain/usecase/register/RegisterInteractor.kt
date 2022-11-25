package com.reift.core.domain.usecase.register

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.User
import com.reift.core.domain.repository.RegisterRepository

class RegisterInteractor(
    val registerRepository: RegisterRepository
): RegisterUseCase {
    override fun checkIfEmailTaken(email: String): LiveData<Boolean> {
        return registerRepository.checkIfEmailTaken(email)
    }

    override fun checkIfUsernameTaken(username: String): LiveData<Boolean> {
        return registerRepository.checkIfUsernameTaken(username)
    }

    override fun saveUser(user: User) {
        return registerRepository.saveUser(user)
    }
}