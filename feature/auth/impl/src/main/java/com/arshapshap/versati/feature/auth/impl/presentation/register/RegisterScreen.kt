package com.arshapshap.versati.feature.auth.impl.presentation.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arshapshap.versati.feature.auth.impl.presentation.register.contract.RegisterSideEffect
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

internal class RegisterScreen {

    @Composable
    fun Content() {
        val viewModel = getViewModel<RegisterViewModel>()
        val state by viewModel.collectAsState()
        viewModel.collectSideEffect {
            when (it) {
                RegisterSideEffect.NavigateToSignIn -> TODO()
            }
        }

        RegisterContent(state = state, viewModel = viewModel)
    }
}