package com.arshapshap.versati.feature.auth.impl.presentation.register.contract

internal data class RegisterState(
    val email: String = "",
    val password: String = "",
    val loading: Boolean = false,
    val success: Boolean = false,
    val showEmailFieldError: Boolean = false,
    val showPasswordFieldError: Boolean = false,
    val emailFieldError: com.arshapshap.versati.feature.auth.impl.presentation.common.contract.EmailFieldError? = null,
    val passwordFieldError: com.arshapshap.versati.feature.auth.impl.presentation.common.contract.PasswordFieldError? = null,
    val registerError: RegisterErrorWithMessage? = null,
)