package com.reift.core.domain.repository

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.User

interface EditRepository {
    fun editUsername(username: String)
    fun editName(name: String)
    fun editBio(bio: String)
    fun editPhotoUrl(photoUrl: String)
    fun checkIfUserTaken(username: String): LiveData<Boolean>
    fun getCurrentUser(): LiveData<User>
    fun logout()
}