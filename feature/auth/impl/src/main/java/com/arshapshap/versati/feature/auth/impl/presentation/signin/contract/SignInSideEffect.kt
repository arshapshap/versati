package com.arshapshap.versati.feature.auth.impl.presentation.signin.contract

internal sealed interface SignInSideEffect {

    data object NavigateToRegistration : SignInSideEffect

    data object NavigateToSettings : SignInSideEffect
}