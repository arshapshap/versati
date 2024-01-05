package com.arshapshap.versati.feature.auth.api.domain.usecase

import com.arshapshap.versati.feature.auth.api.domain.model.RegisterResult
import com.arshapshap.versati.feature.auth.api.domain.repository.AuthRepository

class RegisterUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): RegisterResult {
        return repository.register(email, password)
    }
}