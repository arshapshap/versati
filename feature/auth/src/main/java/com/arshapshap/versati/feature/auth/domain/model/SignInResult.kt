package com.arshapshap.versati.feature.auth.domain.model

internal data class SignInResult(
    val isSuccessful: Boolean,
    val error: SignInError?
)