package com.arshapshap.versati.feature.auth.domain.usecase

import com.arshapshap.versati.feature.auth.domain.repository.AuthRepository

internal class RegisterUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, username: String, password: String) {
        repository.register(email, username, password)
    }
}