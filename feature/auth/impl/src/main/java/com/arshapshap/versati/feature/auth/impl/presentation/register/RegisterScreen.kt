package com.arshapshap.versati.feature.auth.impl.presentation.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.arshapshap.versati.core.navigation.state.AppBarState
import com.arshapshap.versati.feature.auth.impl.presentation.register.contract.RegisterSideEffect
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

object RegisterScreen {

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
            }
        }

        SideEffect {
            appBarConfigure(getAppBarState())
        }
        RegisterContent(state = state, viewModel = viewModel)
    }

    private fun getAppBarState() = AppBarState(
        showArrowBack = true
    )
}