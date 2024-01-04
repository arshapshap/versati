package com.arshapshap.versati.feature.auth.impl.presentation.signin.contract

import androidx.annotation.StringRes
import com.arshapshap.versati.feature.auth.impl.R

internal enum class SignInErrorWithMessage(@StringRes val res: Int) {
    WrongEmailOrPassword(R.string.incorrect_email_or_password),
    UserDisabled(R.string.user_disabled),
    UserNotFound(R.string.user_not_found),
    UnknownError(R.string.unknown_error)
}