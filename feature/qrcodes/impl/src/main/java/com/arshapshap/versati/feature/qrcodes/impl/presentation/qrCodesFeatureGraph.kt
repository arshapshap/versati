package com.arshapshap.versati.feature.qrcodes.impl.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.arshapshap.versati.core.navigation.QRCodesFeature
import com.arshapshap.versati.core.navigation.state.AppBarState
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.QRCodeGenerationScreen
import com.arshapshap.versati.feature.qrcodes.impl.presentation.requesthistory.RequestHistoryScreen

fun NavGraphBuilder.qrCodesFeatureGraph(
    navController: NavHostController,
    appBarConfigure: (AppBarState) -> Unit
) {
    navigation(
        route = QRCodesFeature.featureRoute,
        startDestination = QRCodesFeature.QRCodeGeneration.route
    ) {
        composable(
            route = QRCodesFeature.QRCodeGeneration.route,
            arguments = QRCodesFeature.QRCodeGeneration.arguments
        ) {
//            BackHandler { }

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
}