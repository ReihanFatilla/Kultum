package com.reift.core.domain.usecase.edit

import androidx.lifecycle.LiveData

class EditInteractor: EditUseCase {
    override fun saveEditedProfile(username: String, name: String, bio: String, photoUrl: String) {
        TODO("Not yet implemented")
    }

    override fun checkIfUserTaken(username: String): LiveData<Boolean> {
        TODO("Not yet implemented")
    }
}