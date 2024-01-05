package com.arshapshap.versati.feature.auth.impl.presentation.register.contract

internal sealed interface RegisterSideEffect {
    data object NavigateToSignIn : RegisterSideEffect

    data object NavigateToAccount : RegisterSideEffect
}