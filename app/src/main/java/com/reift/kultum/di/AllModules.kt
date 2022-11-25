package com.reift.kultum.di

import com.reift.core.di.firebaseSourceModule
import com.reift.core.di.localSourceModule
import com.reift.core.di.preferenceModule
import com.reift.core.di.repositoryModule

val listModules = listOf(
    firebaseSourceModule,
    localSourceModule,
    repositoryModule,
    useCaseModule,
    viewModelModule,
    preferenceModule
)