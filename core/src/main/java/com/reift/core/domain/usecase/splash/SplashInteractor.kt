package com.reift.core.domain.usecase.splash

import com.reift.core.domain.repository.SplashRepository

class SplashInteractor(
    private val splashRepository: SplashRepository
): SplashUseCase {
    override fun isUserLogin(): Boolean {
        return splashRepository.isUserLogin()
    }
}