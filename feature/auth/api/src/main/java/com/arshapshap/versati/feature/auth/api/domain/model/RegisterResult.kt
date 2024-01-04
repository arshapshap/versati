package com.arshapshap.versati.feature.auth.api.domain.model

data class RegisterResult(
    val isSuccessful: Boolean,
    val error: RegisterError?
)