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
            "ERROR_WRONG_PASSWORD" -> SignInError.WrongPassword
            "ERROR_INVALID_EMAIL" -> SignInError.InvalidEmail
            "ERROR_USER_DISABLED" -> SignInError.UserDisabled
            "ERROR_USER_NOT_FOUND" -> SignInError.UserNotFound
            else -> SignInError.UnknownError
        }

    private fun detectRegisterError(exception: Exception?): RegisterError? =
        when ((exception as? FirebaseAuthException)?.errorCode) {
            null -> null
            "ERROR_EMAIL_ALREADY_IN_USE" -> RegisterError.EmailAlreadyInUse
            "ERROR_INVALID_EMAIL" -> RegisterError.InvalidEmail
            "ERROR_WEAK_PASSWORD" -> RegisterError.WeakPassword
            else -> RegisterError.UnknownError
        }
}