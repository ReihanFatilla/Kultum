package com.reift.core.domain.usecase.profile

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.model.User
import com.reift.core.domain.repository.ProfileRepository

class ProfileInteractor(
    val profileRepository: ProfileRepository
): ProfileUseCase {
    override fun getUserDetail(): LiveData<User> {
        return profileRepository.getUserDetail()
    }

    override fun getPostedKultum(): LiveData<List<Kultum>> {
        return profileRepository.getPostedKultum()
    }

    override fun getHelpfulKultum(): LiveData<List<Kultum>> {
        return profileRepository.getHelpfulKultum()
    }
}