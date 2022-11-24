package com.reift.core.domain.usecase.edit

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.User

interface EditUseCase {
    fun saveEditedProfile(username: String, name: String, bio: String, photoUrl: String)
    fun checkIfUserTaken(username: String): LiveData<Boolean>
}