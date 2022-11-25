package com.reift.core.domain.usecase.edit

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.User

interface EditUseCase {
    fun saveEditedProfile(username: String? = null, name: String? = null, bio: String? = null, photoUrl: String? = null)
    fun checkIfUserTaken(username: String): LiveData<Boolean>
}