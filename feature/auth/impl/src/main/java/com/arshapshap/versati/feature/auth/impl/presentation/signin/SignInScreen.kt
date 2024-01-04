package com.arshapshap.versati.feature.auth.impl.presentation.signin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arshapshap.versati.core.navigation.AuthFeatureScreen
import com.arshapshap.versati.feature.auth.impl.presentation.signin.contract.SignInSideEffect
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

internal class SignInScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val registerScreen = rememberScreen(AuthFeatureScreen.Register)

        val screenModel = getScreenModel<SignInScreenModel>()
        val state by screenModel.collectAsState()
        screenModel.collectSideEffect {
            when (it) {
                SignInSideEffect.NavigateToRegistration -> navigator.push(registerScreen)
            }
        }

        SignInContent(state = state, screenModel = screenModel)
    }
}