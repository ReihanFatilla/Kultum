package com.reift.kultum.presentation.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.reift.core.domain.usecase.edit.EditUseCase

class EditViewModel(
    val editUseCase: EditUseCase
): ViewModel() {

    fun editUsername(username: String){
        editUseCase.editUsername(username)
    }

    fun editName(name: String){
        editUseCase.editName(name)
    }

    fun editBio(bio: String){
        editUseCase.editBio(bio)
    }

    fun editPhotoUrl(photoUrl: String){
        editUseCase.editPhotoUrl(photoUrl)
    }

    fun checkIfUserTaken(username: String): LiveData<Boolean>{
        return editUseCase.checkIfUserTaken(username)
    }

}