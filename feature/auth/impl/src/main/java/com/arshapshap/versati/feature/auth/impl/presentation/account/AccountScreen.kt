package com.arshapshap.versati.feature.auth.impl.presentation.account

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.arshapshap.versati.core.navigation.AuthFeature
import com.arshapshap.versati.core.navigation.state.AppBarState
import com.arshapshap.versati.feature.auth.impl.R
import com.arshapshap.versati.feature.auth.impl.presentation.account.contract.AccountSideEffect
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

object AccountScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        appBarConfigure: (AppBarState) -> Unit
    ) {
        val viewModel = getViewModel<AccountViewModel>()
        val state by viewModel.collectAsState()
        viewModel.collectSideEffect {
            when (it) {
                AccountSideEffect.NavigateToSignIn -> navController.navigate(AuthFeature.SignIn.destination()) {
                    popUpTo(AuthFeature.Account.route) {
                        inclusive = true
                    }
                }
            }
        }

        val context = LocalContext.current
        SideEffect {
            appBarConfigure(getAppBarState(context))
        }
        AccountContent(state = state, viewModel = viewModel)
    }

    private fun getAppBarState(context: Context) = AppBarState(
        currentRoute = AuthFeature.Account.route,
        title = context.getString(R.string.account),
        showArrowBack = true,
        showBottomBar = false
    )
}