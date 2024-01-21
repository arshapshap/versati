package com.arshapshap.versati.feature.settings.impl.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.arshapshap.versati.core.navigation.features.SettingsFeature
import com.arshapshap.versati.core.navigation.state.AppBarState
import com.arshapshap.versati.feature.settings.impl.presentation.settings.SettingsScreen

fun NavGraphBuilder.settingsFeatureGraph(
    navController: NavHostController,
    appBarConfigure: (AppBarState) -> Unit
) {
    navigation(
        route = SettingsFeature.featureRoute,
        startDestination = SettingsFeature.Settings.route
    ) {
        composable(
            route = SettingsFeature.Settings.route
        ) {
            SettingsScreen.Content(
                navController = navController,
                appBarConfigure = appBarConfigure
            )
        }
    }
}