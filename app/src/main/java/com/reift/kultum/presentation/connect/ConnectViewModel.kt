package com.reift.kultum.presentation.connect

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.model.User
import com.reift.core.domain.usecase.connect.ConnectUseCase

class ConnectViewModel(
    val connectUseCase: ConnectUseCase
): ViewModel() {

    fun getUserByUsername(username: String): LiveData<User>{
        return connectUseCase.getUserByUsername(username)
    }

    fun getPostedKultum(username: String): LiveData<List<Kultum>>{
        return connectUseCase.getPostedKultum(username)
    }

    fun getHelpfulKultum(username: String): LiveData<List<Kultum>>{
        return connectUseCase.getHelpfulKultum(username)
    }

}