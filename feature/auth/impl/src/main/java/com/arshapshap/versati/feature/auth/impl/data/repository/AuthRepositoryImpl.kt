package com.arshapshap.versati.feature.auth.impl.data.repository

import com.arshapshap.versati.feature.auth.api.domain.model.RegisterResult
import com.arshapshap.versati.feature.auth.api.domain.model.SignInResult
import com.arshapshap.versati.feature.auth.api.domain.model.User
import com.arshapshap.versati.feature.auth.api.domain.repository.AuthRepository
import com.arshapshap.versati.feature.auth.impl.data.mapper.AuthMapper
import com.google.firebase.auth.FirebaseAuth
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

internal class AuthRepositoryImpl(
    private val mapper: AuthMapper
) : AuthRepository {

    override suspend fun signIn(email: String, password: String): SignInResult =
        suspendCoroutine { continuation ->
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    continuation.resume(
                        SignInResult(
                            isSuccessful = it.isSuccessful,
                            error = mapper.mapToSignInError(it.exception)
                        )
                    )
                }
        }

    override suspend fun register(email: String, password: String): RegisterResult =
        suspendCoroutine { continuation ->
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    continuation.resume(
                        RegisterResult(
                            isSuccessful = it.isSuccessful,
                            error = mapper.mapToRegisterError(it.exception)
                        )
                    )
                }
        }

    override suspend fun logOut() {
        FirebaseAuth.getInstance().signOut()
    }

    override suspend fun getCurrentUser(): User? {
        return FirebaseAuth.getInstance().currentUser?.let { mapper.mapToUser(it) }
    }
}