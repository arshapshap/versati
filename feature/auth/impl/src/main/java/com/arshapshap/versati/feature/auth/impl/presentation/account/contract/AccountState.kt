package com.arshapshap.versati.feature.auth.impl.presentation.account.contract

import com.arshapshap.versati.feature.auth.api.domain.model.User

internal data class AccountState(
    val user: User? = null,
    val showDialogToConfirmLogOut: Boolean = false
)