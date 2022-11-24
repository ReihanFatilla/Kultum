package com.reift.core.data.repository

import androidx.lifecycle.LiveData
import com.reift.core.domain.repository.EditRepository

class EditRepositoryImpl(

): EditRepository {
    override fun saveEditedProfile(username: String, name: String, bio: String, photoUrl: String) {
        TODO("Not yet implemented")
    }

    override fun checkIfUserTaken(username: String): LiveData<Boolean> {
        TODO("Not yet implemented")
    }

}