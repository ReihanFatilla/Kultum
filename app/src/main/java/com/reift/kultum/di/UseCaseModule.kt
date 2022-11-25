package com.reift.kultum.di

import com.reift.core.domain.usecase.connect.ConnectInteractor
import com.reift.core.domain.usecase.connect.ConnectUseCase
import com.reift.core.domain.usecase.edit.EditInteractor
import com.reift.core.domain.usecase.edit.EditUseCase
import com.reift.core.domain.usecase.follow.FollowInteractor
import com.reift.core.domain.usecase.follow.FollowUseCase
import com.reift.core.domain.usecase.home.HomeInteractor
import com.reift.core.domain.usecase.home.HomeUseCase
import com.reift.core.domain.usecase.login.LoginInteractor
import com.reift.core.domain.usecase.login.LoginUseCase
import com.reift.core.domain.usecase.profile.ProfileInteractor
import com.reift.core.domain.usecase.profile.ProfileUseCase
import com.reift.core.domain.usecase.register.RegisterInteractor
import com.reift.core.domain.usecase.register.RegisterUseCase
import com.reift.core.domain.usecase.splash.SplashInteractor
import com.reift.core.domain.usecase.splash.SplashUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<ConnectUseCase> { ConnectInteractor(get()) }
    single<EditUseCase> { EditInteractor(get()) }
    single<FollowUseCase> { FollowInteractor(get()) }
    single<HomeUseCase> { HomeInteractor(get()) }
    single<LoginUseCase> { LoginInteractor(get()) }
    single<ProfileUseCase> { ProfileInteractor(get()) }
    single<RegisterUseCase> { RegisterInteractor(get()) }
    single<SplashUseCase> { SplashInteractor(get()) }
}