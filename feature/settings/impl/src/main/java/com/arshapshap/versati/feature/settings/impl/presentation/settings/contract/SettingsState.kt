package com.arshapshap.versati.feature.settings.impl.presentation.settings.contract

import androidx.compose.runtime.Immutable
import com.arshapshap.versati.feature.auth.api.domain.model.User

@Immutable
internal data class SettingsState(
    val user: User? = null,
    val userLoading: Boolean = false,
    val showDialogToConfirmLogOut: Boolean = false
)