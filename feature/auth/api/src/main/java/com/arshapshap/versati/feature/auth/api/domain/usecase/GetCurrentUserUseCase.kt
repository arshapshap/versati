package com.arshapshap.versati.feature.auth.api.domain.usecase

import com.arshapshap.versati.feature.auth.api.domain.model.User
import com.arshapshap.versati.feature.auth.api.domain.repository.AuthRepository

class GetCurrentUserUseCase internal constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): User? {
        return repository.getCurrentUser()
    }
}