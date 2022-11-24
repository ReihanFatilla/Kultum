package com.reift.core.domain.usecase.profile

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.model.User
import com.reift.core.domain.repository.ProfileRepository

class ProfileInteractor(
    val profileRepository: ProfileRepository
): ProfileUseCase {
    override fun getUserDetail(username: String): LiveData<User> {
        return profileRepository.getUserDetail(username)
    }

    override fun getPostedKultum(username: String): LiveData<List<Kultum>> {
        return profileRepository.getPostedKultum(username)
    }

    override fun getHelpfulKultum(username: String): LiveData<List<Kultum>> {
        return profileRepository.getHelpfulKultum(username)
    }
}