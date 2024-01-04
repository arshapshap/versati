package com.arshapshap.versati.feature.auth.presentation.signin.contract

import androidx.annotation.StringRes
import com.arshapshap.versati.feature.auth.R

internal enum class PasswordFieldError(@StringRes val res: Int) {
    EmptyPassword(R.string.enter_password)
}