package com.arshapshap.versati.feature.auth.domain.model

enum class SignInError {
    WrongPassword,
    InvalidEmail,
    UserDisabled,
    UserNotFound,
    UnknownError
}