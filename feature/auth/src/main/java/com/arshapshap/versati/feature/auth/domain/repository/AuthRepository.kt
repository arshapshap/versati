package com.arshapshap.versati.feature.auth.domain.repository

import com.arshapshap.versati.feature.auth.domain.model.RegisterResult
import com.arshapshap.versati.feature.auth.domain.model.SignInResult
import com.google.firebase.auth.FirebaseUser

internal interface AuthRepository {

    suspend fun signIn(email: String, password: String): SignInResult

    suspend fun register(email: String, password: String): RegisterResult

    suspend fun logOut()

    suspend fun getCurrentUser(): FirebaseUser?
}