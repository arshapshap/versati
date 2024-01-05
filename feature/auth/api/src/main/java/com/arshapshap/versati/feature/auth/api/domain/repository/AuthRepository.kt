package com.arshapshap.versati.feature.auth.api.domain.repository

import com.arshapshap.versati.feature.auth.api.domain.model.RegisterResult
import com.arshapshap.versati.feature.auth.api.domain.model.SignInResult
import com.arshapshap.versati.feature.auth.api.domain.model.User

interface AuthRepository {

    suspend fun signIn(email: String, password: String): SignInResult

    suspend fun register(email: String, password: String): RegisterResult

    suspend fun logOut()

    suspend fun getCurrentUser(): User?
}