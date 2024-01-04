package com.arshapshap.versati.feature.auth.impl.presentation.register.contract

import androidx.annotation.StringRes
import com.arshapshap.versati.feature.auth.impl.R

internal enum class RegisterErrorWithMessage(@StringRes val res: Int) {
    UnknownError(R.string.unknown_error)
}