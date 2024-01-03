package com.arshapshap.versati.feature.auth.domain.repository

import com.google.firebase.auth.FirebaseUser

internal interface AuthRepository {

    suspend fun logIn(email: String, password: String)

    suspend fun register(email: String, username: String, password: String)

    suspend fun logOut()

    suspend fun getCurrentUser(): FirebaseUser?
}