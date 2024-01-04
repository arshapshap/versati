package com.arshapshap.versati.feature.auth.domain.model

enum class RegisterError {
    EmailAlreadyInUse,
    InvalidEmail,
    WeakPassword,
    UnknownError,
}