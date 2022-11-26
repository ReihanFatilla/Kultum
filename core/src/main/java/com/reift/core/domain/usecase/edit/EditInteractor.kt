package com.reift.core.domain.usecase.edit

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.User
import com.reift.core.domain.repository.EditRepository

class EditInteractor(
    val editRepository: EditRepository
): EditUseCase {

    override fun editUsername(username: String) {
        editRepository.editUsername(username)
    }

    override fun editName(name: String) {
        editRepository.editName(name)
    }

    override fun editBio(bio: String) {
        editRepository.editBio(bio)
    }

    override fun editPhotoUrl(photoUrl: String) {
        editRepository.editPhotoUrl(photoUrl)
    }

    override fun checkIfUserTaken(username: String): LiveData<Boolean> {
        return editRepository.checkIfUserTaken(username)
    }

    override fun getCurrentUser(): LiveData<User> {
        return editRepository.getCurrentUser()
    }
}