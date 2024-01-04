package com.arshapshap.versati.feature.auth.presentation.signin.contract

import androidx.annotation.StringRes
import com.arshapshap.versati.feature.auth.R

internal enum class EmailFieldError(@StringRes val res: Int) {
    EmptyEmail(R.string.enter_email),
    InvalidEmail(R.string.invalid_email)
}