package com.reift.kultum.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.usecase.home.HomeUseCase

class HomeViewModel(
    val homeUseCase: HomeUseCase
): ViewModel() {

    val kultum = MutableLiveData<Kultum>()

    fun getKultumForYou(): LiveData<List<Kultum>> {
        return homeUseCase.getKultumForYou()
    }

    fun getKultumDetail(urlKultum: String): LiveData<Kultum>{
        return homeUseCase.getKultumDetail(urlKultum)
    }

    fun isKultumHelpfuled(urlKultum: String): LiveData<Boolean>{
        return homeUseCase.isKultumHelpfuled(urlKultum)
    }

    fun addHelpfulKultum(urlKultum: String){
        homeUseCase.addHelpfulKultum(urlKultum)
    }

    fun removeKultum(urlKultum: String){
        homeUseCase.removeHelpfulKultum(urlKultum)
    }

}