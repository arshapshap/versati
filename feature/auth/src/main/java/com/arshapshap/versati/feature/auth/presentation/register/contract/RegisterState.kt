package com.arshapshap.versati.feature.auth.presentation.register.contract

import com.arshapshap.versati.feature.auth.presentation.common.contract.EmailFieldError
import com.arshapshap.versati.feature.auth.presentation.common.contract.PasswordFieldError

internal data class RegisterState(
    val email: String = "",
    val password: String = "",
    val loading: Boolean = false,
    val success: Boolean = false,
    val showEmailFieldError: Boolean = false,
    val showPasswordFieldError: Boolean = false,
    val emailFieldError: EmailFieldError? = null,
    val passwordFieldError: PasswordFieldError? = null,
    val registerError: RegisterErrorWithMessage? = null,
)