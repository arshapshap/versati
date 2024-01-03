package com.arshapshap.versati.feature.auth.domain.usecase

import com.arshapshap.versati.feature.auth.domain.repository.AuthRepository

internal class LogInUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String) {
        repository.logIn(email, password)
    }
}