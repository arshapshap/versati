package com.arshapshap.versati.feature.auth.data.repository

import com.arshapshap.versati.feature.auth.domain.model.RegisterError
import com.arshapshap.versati.feature.auth.domain.model.RegisterResult
import com.arshapshap.versati.feature.auth.domain.model.SignInError
import com.arshapshap.versati.feature.auth.domain.model.SignInResult
import com.arshapshap.versati.feature.auth.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

internal class AuthRepositoryImpl : AuthRepository {

    override suspend fun signIn(email: String, password: String): SignInResult =
        suspendCoroutine { continuation ->
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    continuation.resume(
                        SignInResult(
                            isSuccessful = it.isSuccessful,
                            error = detectSignInError(it.exception)
                        )
                    )
                }
        }

    override suspend fun register(email: String, password: String): RegisterResult =
        suspendCoroutine { continuation ->
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    continuation.resume(
                        RegisterResult(
                            isSuccessful = it.isSuccessful,
                            error = detectRegisterError(it.exception)
                        )
                    )
                }
        }

    override suspend fun logOut() {
        FirebaseAuth.getInstance().signOut()
    }

    override suspend fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }

    private fun detectSignInError(exception: Exception?): SignInError? =
        when ((exception as? FirebaseAuthException)?.errorCode) {
            null -> null
            "ERROR_INVALID_CREDENTIAL" -> SignInError.WrongPassword
            "wrong-password" -> SignInError.WrongPassword
            "invalid-email" -> SignInError.InvalidEmail
            "user-disabled" -> SignInError.UserDisabled
            "user-not-found" -> SignInError.UserNotFound
            else -> SignInError.UnknownError
        }

    private fun detectRegisterError(exception: Exception?): RegisterError? =
        when ((exception as? FirebaseAuthException)?.errorCode) {
            null -> null
            "email-already-in-use" -> RegisterError.EmailAlreadyInUse
            "invalid-email" -> RegisterError.InvalidEmail
            "weak-password" -> RegisterError.WeakPassword
            else -> RegisterError.UnknownError
        }
}