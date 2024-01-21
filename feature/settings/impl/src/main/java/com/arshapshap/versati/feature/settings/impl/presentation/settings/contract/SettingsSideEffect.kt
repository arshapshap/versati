package com.arshapshap.versati.feature.settings.impl.presentation.settings.contract

internal sealed interface SettingsSideEffect {

    data object NavigateToSignIn : SettingsSideEffect
}