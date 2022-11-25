package com.reift.core.domain.repository

import androidx.lifecycle.LiveData

interface EditRepository {
    fun saveEditedProfile(username: String? = null, name: String? = null, bio: String? = null, photoUrl: String? = null)
    fun checkIfUserTaken(username: String): LiveData<Boolean>
}