package com.arshapshap.versati.feature.auth.domain.usecase

import com.arshapshap.versati.feature.auth.domain.model.SignInResult
import com.arshapshap.versati.feature.auth.domain.repository.AuthRepository

internal class SignInUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): SignInResult {
        return repository.signIn(email, password)
    }
}