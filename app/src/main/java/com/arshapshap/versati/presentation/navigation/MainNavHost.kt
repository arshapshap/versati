package com.arshapshap.versati.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arshapshap.versati.core.navigation.AuthFeature
import com.arshapshap.versati.core.navigation.ImageParsingFeature
import com.arshapshap.versati.core.navigation.QRCodesFeature
import com.arshapshap.versati.core.navigation.state.AppBarState
import com.arshapshap.versati.feature.auth.impl.presentation.account.AccountScreen
import com.arshapshap.versati.feature.auth.impl.presentation.register.RegisterScreen
import com.arshapshap.versati.feature.auth.impl.presentation.signin.SignInScreen
import com.arshapshap.versati.feature.imageparsing.impl.presentation.parsing.ParsingScreen
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.QRCodeGenerationScreen
import com.arshapshap.versati.feature.qrcodes.impl.presentation.requesthistory.RequestHistoryScreen

@Composable
internal fun MainNavHost(
    modifier: Modifier,
    navController: NavHostController,
    appBarConfigure: (AppBarState) -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ImageParsingFeature.Parsing.route //QRCodesFeature.QRCodeGeneration.route
    ) {
        authFeature(navController, appBarConfigure)
        qrCodesFeature(navController, appBarConfigure)
        imageParsingFeature(navController, appBarConfigure)
    }
}

private fun NavGraphBuilder.authFeature(
    navController: NavHostController,
    appBarConfigure: (AppBarState) -> Unit
) {
    composable(
        route = AuthFeature.Account.route
    ) {
        AccountScreen.Content(
            navController = navController,
            appBarConfigure = appBarConfigure
        )
    }
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

private fun NavGraphBuilder.qrCodesFeature(
    navController: NavHostController,
    appBarConfigure: (AppBarState) -> Unit
) {
    composable(
        route = QRCodesFeature.QRCodeGeneration.route,
        arguments = QRCodesFeature.QRCodeGeneration.arguments
    ) {
        QRCodeGenerationScreen.Content(
            navController = navController,
            id = it.arguments?.getLong(QRCodesFeature.QRCodeGeneration.idArgument),
            appBarConfigure = appBarConfigure
        )
    }
    composable(
        route = QRCodesFeature.RequestHistory.route
    ) {
        RequestHistoryScreen.Content(
            navController = navController,
            appBarConfigure = appBarConfigure
        )
    }
}

private fun NavGraphBuilder.imageParsingFeature(
    navController: NavHostController,
    appBarConfigure: (AppBarState) -> Unit
) {
    composable(
        route = ImageParsingFeature.Parsing.route,
        arguments = ImageParsingFeature.Parsing.arguments
    ) {
        ParsingScreen.Content(
            navController = navController,
            id = it.arguments?.getLong(ImageParsingFeature.Parsing.idArgument),
            appBarConfigure = appBarConfigure
        )
    }
}