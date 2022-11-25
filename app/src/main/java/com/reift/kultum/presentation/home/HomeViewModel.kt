package com.reift.kultum.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.usecase.home.HomeUseCase

class HomeViewModel(
    val homeUseCase: HomeUseCase
): ViewModel() {

    fun getKultumForYou(): LiveData<List<Kultum>> {
        return homeUseCase.getKultumForYou()
    }

    fun addHelpfulKultum(urlKultum: String){
        homeUseCase.addHelpfulKultum(urlKultum)
    }

    fun removeKultum(urlKultum: String){
        homeUseCase.removeHelpfulKultum(urlKultum)
    }

}