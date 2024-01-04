package com.arshapshap.versati.feature.auth.api.domain.usecase

import com.arshapshap.versati.feature.auth.api.domain.model.SignInResult
import com.arshapshap.versati.feature.auth.api.domain.repository.AuthRepository

class SignInUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): SignInResult {
        return repository.signIn(email, password)
    }
}