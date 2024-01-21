package com.arshapshap.versati.feature.auth.impl.presentation.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.arshapshap.versati.core.navigation.features.AuthFeature
import com.arshapshap.versati.core.navigation.features.SettingsFeature
import com.arshapshap.versati.core.navigation.state.AppBarState
import com.arshapshap.versati.feature.auth.impl.presentation.register.contract.RegisterSideEffect
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

internal object RegisterScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        appBarConfigure: (AppBarState) -> Unit
    ) {
        val viewModel = getViewModel<RegisterViewModel>()
        val state by viewModel.collectAsState()
        viewModel.collectSideEffect {
            when (it) {
                RegisterSideEffect.NavigateToSignIn -> navController.popBackStack()
                RegisterSideEffect.NavigateToSettings -> navController.navigate(SettingsFeature.Settings.destination()) {
                    popUpTo(SettingsFeature.Settings.route) {
                        inclusive = true
                    }
                }
            }
        }

        SideEffect {
            appBarConfigure(getAppBarState())
        }
        RegisterContent(state = state, viewModel = viewModel)
    }

    private fun getAppBarState() = AppBarState(
        currentRoute = AuthFeature.Register.route,
        showArrowBack = true,
        showBottomBar = false
    )
}