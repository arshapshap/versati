package com.arshapshap.versati.feature.auth.impl.data.mapper

import com.arshapshap.versati.feature.auth.api.domain.model.RegisterError
import com.arshapshap.versati.feature.auth.api.domain.model.SignInError
import com.arshapshap.versati.feature.auth.api.domain.model.User
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser

internal class AuthMapper {

    fun mapToSignInError(exception: Exception?): SignInError? =
        when ((exception as? FirebaseAuthException)?.errorCode) {
            null -> null
            "ERROR_INVALID_CREDENTIAL" -> SignInError.WrongPassword
            "ERROR_WRONG_PASSWORD" -> SignInError.WrongPassword
            "ERROR_INVALID_EMAIL" -> SignInError.InvalidEmail
            "ERROR_USER_DISABLED" -> SignInError.UserDisabled
            "ERROR_USER_NOT_FOUND" -> SignInError.UserNotFound
            else -> SignInError.UnknownError
        }

    fun mapToRegisterError(exception: Exception?): RegisterError? =
        when ((exception as? FirebaseAuthException)?.errorCode) {
            null -> null
            "ERROR_EMAIL_ALREADY_IN_USE" -> RegisterError.EmailAlreadyInUse
            "ERROR_INVALID_EMAIL" -> RegisterError.InvalidEmail
            "ERROR_WEAK_PASSWORD" -> RegisterError.WeakPassword
            else -> RegisterError.UnknownError
        }

    fun mapToUser(firebaseUser: FirebaseUser): User = User(
        uid = firebaseUser.uid,
        email = firebaseUser.email ?: ""
    )
}