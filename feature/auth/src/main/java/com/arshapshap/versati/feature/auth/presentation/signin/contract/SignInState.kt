package com.arshapshap.versati.feature.auth.presentation.signin.contract

internal data class SignInState(
    val email: String = "",
    val password: String = "",
    val loading: Boolean = false,
    val success: Boolean = false,
    val showEmailFieldError: Boolean = false,
    val showPasswordFieldError: Boolean = false,
    val emailFieldError: EmailFieldError? = null,
    val passwordFieldError: PasswordFieldError? = null,
    val signInError: SignInErrorWithMessage? = null,
)