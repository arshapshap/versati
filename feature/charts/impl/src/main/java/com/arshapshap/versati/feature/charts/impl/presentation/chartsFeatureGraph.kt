package com.arshapshap.versati.feature.charts.impl.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.arshapshap.versati.core.navigation.ChartsFeature
import com.arshapshap.versati.core.navigation.state.AppBarState
import com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.ChartGenerationScreen
import com.arshapshap.versati.feature.charts.impl.presentation.chartshistory.ChartsHistoryScreen

fun NavGraphBuilder.chartsFeatureGraph(
    navController: NavHostController,
    appBarConfigure: (AppBarState) -> Unit
) {
    navigation(
        route = ChartsFeature.featureRoute,
        startDestination = ChartsFeature.ChartGeneration.route
    ) {
        composable(
            route = ChartsFeature.ChartGeneration.route,
            arguments = ChartsFeature.ChartGeneration.arguments
        ) {
            ChartGenerationScreen.Content(
                navController = navController,
                id = it.arguments?.getLong(ChartsFeature.ChartGeneration.idArgument),
                appBarConfigure = appBarConfigure
            )
        }
        composable(
            route = ChartsFeature.ChartsHistory.route
        ) {
            ChartsHistoryScreen.Content(
                navController = navController,
                appBarConfigure = appBarConfigure
            )
        }
    }
}