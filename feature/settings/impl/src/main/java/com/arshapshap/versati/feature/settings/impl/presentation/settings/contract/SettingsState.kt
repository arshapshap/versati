package com.arshapshap.versati.feature.settings.impl.presentation.settings.contract

import com.arshapshap.versati.feature.auth.api.domain.model.User

internal data class SettingsState(
    val user: User? = null,
    val userLoading: Boolean = false,
    val showDialogToConfirmLogOut: Boolean = false
)