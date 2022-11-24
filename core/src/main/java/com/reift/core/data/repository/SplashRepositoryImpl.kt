package com.reift.core.data.repository

import com.reift.core.constant.Pref
import com.reift.core.data.source.local.LocalDataSource
import com.reift.core.domain.repository.SplashRepository

class SplashRepositoryImpl(
    private val localDataSource: LocalDataSource
): SplashRepository {
    override fun isUserLogin(): Boolean {
        return localDataSource.getBoolean(Pref.IS_USER_LOGIN)
    }
}