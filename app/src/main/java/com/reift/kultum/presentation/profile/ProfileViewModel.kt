package com.reift.kultum.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.model.User
import com.reift.core.domain.usecase.profile.ProfileUseCase

class ProfileViewModel(
    val profileUseCase: ProfileUseCase
): ViewModel() {

    fun getUserDetail(): LiveData<User>{
        return profileUseCase.getUserDetail()
    }

    fun getPostedKultum(): LiveData<List<Kultum>> {
        return profileUseCase.getPostedKultum()
    }

    fun getHelpfuledKultum(): LiveData<List<Kultum>> {
        return profileUseCase.getHelpfulKultum()
    }

}