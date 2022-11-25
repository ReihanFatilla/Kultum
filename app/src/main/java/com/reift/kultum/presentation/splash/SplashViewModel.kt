package com.reift.kultum.presentation.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reift.core.domain.usecase.splash.SplashUseCase

class SplashViewModel(
    val splashUseCase: SplashUseCase
): ViewModel() {

    val isLogin = MutableLiveData(splashUseCase.isUserLogin())

}