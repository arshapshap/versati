package com.arshapshap.versati.feature.auth.domain.usecase

import com.arshapshap.versati.feature.auth.domain.repository.AuthRepository

class LogOutUseCase internal constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke() {
        repository.logOut()
    }
}