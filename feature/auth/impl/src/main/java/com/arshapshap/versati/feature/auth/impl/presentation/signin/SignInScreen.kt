package com.arshapshap.versati.feature.auth.impl.presentation.signin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.arshapshap.versati.core.navigation.AuthFeature
import com.arshapshap.versati.core.navigation.state.AppBarState
import com.arshapshap.versati.feature.auth.impl.presentation.signin.contract.SignInSideEffect
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

object SignInScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        appBarConfigure: (AppBarState) -> Unit
    ) {
        val viewModel = getViewModel<SignInViewModel>()
        val state by viewModel.collectAsState()
        viewModel.collectSideEffect {
            when (it) {
                SignInSideEffect.NavigateToRegistration -> navController.navigate(AuthFeature.Register.destination())
                SignInSideEffect.NavigateToAccount -> navController.navigate(AuthFeature.Account.destination()) {
                    popUpTo(AuthFeature.SignIn.route) {
                        inclusive = true
                    }
                }
            }
        }

        SideEffect {
            appBarConfigure(getAppBarState())
        }
        SignInContent(state = state, viewModel = viewModel)
    }

    private fun getAppBarState() = AppBarState(
        showArrowBack = true
    )
}