package com.arshapshap.versati.feature.auth.impl.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.arshapshap.versati.core.navigation.features.AuthFeature
import com.arshapshap.versati.core.navigation.state.AppBarState
import com.arshapshap.versati.feature.auth.impl.presentation.register.RegisterScreen
import com.arshapshap.versati.feature.auth.impl.presentation.signin.SignInScreen

fun NavGraphBuilder.authFeatureGraph(
    navController: NavHostController,
    appBarConfigure: (AppBarState) -> Unit
) {
    navigation(
        route = AuthFeature.featureRoute,
        startDestination = AuthFeature.SignIn.route
    ) {
        composable(
            route = AuthFeature.Register.route
        ) {
            RegisterScreen.Content(
                navController = navController,
                appBarConfigure = appBarConfigure
            )
        }
        composable(
            route = AuthFeature.SignIn.route
        ) {
            SignInScreen.Content(
                navController = navController,
                appBarConfigure = appBarConfigure
            )
        }
    }
}