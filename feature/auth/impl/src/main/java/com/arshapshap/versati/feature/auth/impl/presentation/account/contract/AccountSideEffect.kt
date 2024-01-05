package com.arshapshap.versati.feature.auth.impl.presentation.account.contract

internal sealed interface AccountSideEffect {

    data object NavigateToSignIn : AccountSideEffect
}