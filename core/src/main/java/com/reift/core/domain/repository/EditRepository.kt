package com.reift.core.domain.repository

import androidx.lifecycle.LiveData

interface EditRepository {
    fun saveEditedProfile(username: String, name: String, bio: String, photoUrl: String)
    fun checkIfUserTaken(username: String): LiveData<Boolean>
}