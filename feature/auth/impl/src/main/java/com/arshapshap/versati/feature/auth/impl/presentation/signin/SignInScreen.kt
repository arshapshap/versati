package com.arshapshap.versati.feature.auth.impl.presentation.signin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arshapshap.versati.feature.auth.impl.presentation.signin.contract.SignInSideEffect
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

internal class SignInScreen {

    @Composable
    fun Content() {
        val screenModel = getViewModel<SignInViewModel>()
        val state by screenModel.collectAsState()
        screenModel.collectSideEffect {
            when (it) {
                SignInSideEffect.NavigateToRegistration -> TODO()
            }
        }

        SignInContent(state = state, viewModel = screenModel)
    }
}