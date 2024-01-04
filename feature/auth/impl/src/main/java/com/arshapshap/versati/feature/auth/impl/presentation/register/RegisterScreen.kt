package com.arshapshap.versati.feature.auth.impl.presentation.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arshapshap.versati.feature.auth.impl.presentation.register.contract.RegisterSideEffect
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

internal class RegisterScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val screenModel = getScreenModel<RegisterScreenModel>()
        val state by screenModel.collectAsState()
        screenModel.collectSideEffect {
            when (it) {
                RegisterSideEffect.NavigateToSignIn -> navigator.pop()
            }
        }

        RegisterContent(state = state, screenModel = screenModel)
    }
}