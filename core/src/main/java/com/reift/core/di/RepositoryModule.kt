package com.reift.core.di

import com.reift.core.data.repository.*
import com.reift.core.domain.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    single<ConnectRepository> { ConnectRepositoryImpl(get(), get()) }
    single<EditRepository> { EditRepositoryImpl(get(), get()) }
    single<FollowRepository> { FollowRepositoryImpl(get()) }
    single<HomeRepository> { HomeRepositoryImpl(get(), get()) }
    single<LoginRepository> { LoginRepositoryImpl(get(), get()) }
    single<ProfileRepository> { ProfileRepositoryImpl(get(), get()) }
    single<RegisterRepository> { RegisterRepositoryImpl(get(), get()) }
    single<SplashRepository> { SplashRepositoryImpl(get()) }
    single<PostRepository> { PostRepositoryImpl(get(), get()) }
    single<CommentRepository> { CommentRepositoryImpl(get(), get()) }
}