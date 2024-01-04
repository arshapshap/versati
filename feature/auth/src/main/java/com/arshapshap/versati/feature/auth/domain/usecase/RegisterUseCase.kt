package com.arshapshap.versati.feature.auth.domain.usecase

import com.arshapshap.versati.feature.auth.domain.model.RegisterResult
import com.arshapshap.versati.feature.auth.domain.repository.AuthRepository

internal class RegisterUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): RegisterResult {
        return repository.register(email, password)
    }
}