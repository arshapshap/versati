package com.arshapshap.versati.feature.auth.presentation.register.contract

import androidx.annotation.StringRes
import com.arshapshap.versati.feature.auth.R

internal enum class RegisterErrorWithMessage(@StringRes val res: Int) {
    EmailAlreadyInUse(R.string.email_already_in_use),
    UnknownError(R.string.unknown_error)
}