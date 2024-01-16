package com.arshapshap.versati.feature.imageparsing.impl.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.arshapshap.versati.core.navigation.ImageParsingFeature
import com.arshapshap.versati.core.navigation.state.AppBarState
import com.arshapshap.versati.feature.imageparsing.impl.presentation.history.ParsingHistoryScreen
import com.arshapshap.versati.feature.imageparsing.impl.presentation.parsing.ParsingScreen

fun NavGraphBuilder.imageParsingFeatureGraph(
    navController: NavHostController,
    appBarConfigure: (AppBarState) -> Unit
) {
    navigation(
        route = ImageParsingFeature.featureRoute,
        startDestination = ImageParsingFeature.Parsing.route
    ) {
        composable(
            route = ImageParsingFeature.Parsing.route,
            arguments = ImageParsingFeature.Parsing.arguments
        ) {
//            BackHandler { }

            ParsingScreen.Content(
                navController = navController,
                id = it.arguments?.getLong(ImageParsingFeature.Parsing.idArgument),
                appBarConfigure = appBarConfigure
            )
        }

        composable(
            route = ImageParsingFeature.ParsingHistory.route
        ) {
            ParsingHistoryScreen.Content(
                navController = navController,
                appBarConfigure = appBarConfigure
            )
        }
    }
}