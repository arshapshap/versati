package com.arshapshap.versati.feature.auth.api.domain.model

enum class SignInError {
    WrongPassword,
    InvalidEmail,
    UserDisabled,
    UserNotFound,
    UnknownError
}