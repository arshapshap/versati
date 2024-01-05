package com.arshapshap.versati.feature.auth.impl.presentation.common.contract

import androidx.annotation.StringRes
import com.arshapshap.versati.feature.auth.impl.R

internal enum class PasswordFieldError(@StringRes val res: Int) {
    EmptyPassword(R.string.enter_password),
    WeakPassword(R.string.weak_password),
}