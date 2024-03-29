package com.arshapshap.versati.feature.auth.impl.presentation.common.contract

import androidx.annotation.StringRes
import com.arshapshap.versati.feature.auth.impl.R

internal enum class EmailFieldError(@StringRes val res: Int) {
    EmptyEmail(R.string.enter_email),
    InvalidEmail(R.string.invalid_email),
    EmailAlreadyInUse(R.string.email_already_in_use)
}