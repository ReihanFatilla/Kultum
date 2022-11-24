package com.reift.core.domain.usecase.edit

import androidx.lifecycle.LiveData
import com.reift.core.domain.repository.EditRepository

class EditInteractor(
    val editRepository: EditRepository
): EditUseCase {
    override fun saveEditedProfile(username: String, name: String, bio: String, photoUrl: String) {
        editRepository.saveEditedProfile(username, name, bio, photoUrl)
    }

    override fun checkIfUserTaken(username: String): LiveData<Boolean> {
        return editRepository.checkIfUserTaken(username)
    }
}