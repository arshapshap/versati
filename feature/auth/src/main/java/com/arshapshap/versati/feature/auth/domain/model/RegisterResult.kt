package com.arshapshap.versati.feature.auth.domain.model

internal data class RegisterResult(
    val isSuccessful: Boolean,
    val error: RegisterError?
)