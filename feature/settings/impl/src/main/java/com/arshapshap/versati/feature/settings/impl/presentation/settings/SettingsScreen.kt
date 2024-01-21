package com.arshapshap.versati.feature.settings.impl.presentation.settings

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.arshapshap.versati.core.navigation.features.AuthFeature
import com.arshapshap.versati.core.navigation.features.SettingsFeature
import com.arshapshap.versati.core.navigation.state.AppBarState
import com.arshapshap.versati.feature.settings.impl.R
import com.arshapshap.versati.feature.settings.impl.presentation.settings.contract.SettingsSideEffect
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

internal object SettingsScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        appBarConfigure: (AppBarState) -> Unit
    ) {
        val viewModel = getViewModel<SettingsViewModel>()
        val state by viewModel.collectAsState()
        viewModel.collectSideEffect {
            when (it) {
                SettingsSideEffect.NavigateToSignIn -> navController.navigate(AuthFeature.SignIn.destination())
            }
        }

        val context = LocalContext.current
        SideEffect {
            appBarConfigure(getAppBarState(context))
        }
        SettingsContent(state = state, viewModel = viewModel)
    }

    private fun getAppBarState(context: Context) = AppBarState(
        currentRoute = SettingsFeature.Settings.route,
        title = context.getString(R.string.settings),
        showArrowBack = true,
        showBottomBar = false
    )
}