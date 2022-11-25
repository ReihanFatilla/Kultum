package com.reift.kultum.di

import com.reift.core.domain.usecase.splash.SplashUseCase
import com.reift.kultum.presentation.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
}