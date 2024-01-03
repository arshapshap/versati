package com.arshapshap.versati.feature.auth.domain.usecase

import com.arshapshap.versati.feature.auth.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser

class GetCurrentUserUseCase internal constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): FirebaseUser? {
        return repository.getCurrentUser()
    }
}