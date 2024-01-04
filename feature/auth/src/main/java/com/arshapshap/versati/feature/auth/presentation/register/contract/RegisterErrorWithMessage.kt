package com.arshapshap.versati.feature.auth.presentation.register.contract

import androidx.annotation.StringRes
import com.arshapshap.versati.feature.auth.R

internal enum class RegisterErrorWithMessage(@StringRes val res: Int) {
    UnknownError(R.string.unknown_error)
}