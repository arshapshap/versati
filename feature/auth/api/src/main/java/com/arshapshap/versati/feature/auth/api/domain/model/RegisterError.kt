package com.arshapshap.versati.feature.auth.api.domain.model

enum class RegisterError {
    EmailAlreadyInUse,
    InvalidEmail,
    WeakPassword,
    UnknownError,
}