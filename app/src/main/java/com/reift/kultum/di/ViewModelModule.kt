package com.reift.kultum.di

import com.reift.core.domain.usecase.splash.SplashUseCase
import com.reift.kultum.presentation.comment.CommentViewModel
import com.reift.kultum.presentation.connect.ConnectViewModel
import com.reift.kultum.presentation.edit.EditViewModel
import com.reift.kultum.presentation.home.HomeViewModel
import com.reift.kultum.presentation.login.LoginViewModel
import com.reift.kultum.presentation.post.PostViewModel
import com.reift.kultum.presentation.profile.ProfileViewModel
import com.reift.kultum.presentation.register.RegisterViewModel
import com.reift.kultum.presentation.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { EditViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { PostViewModel(get()) }
    viewModel { ConnectViewModel(get()) }
    viewModel { CommentViewModel(get()) }
}