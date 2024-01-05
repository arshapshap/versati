package com.arshapshap.versati.feature.auth.api.domain.usecase

import com.arshapshap.versati.feature.auth.api.domain.repository.AuthRepository

class LogOutUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke() {
        repository.logOut()
    }
}