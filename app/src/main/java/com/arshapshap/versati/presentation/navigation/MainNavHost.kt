package com.arshapshap.versati.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.arshapshap.versati.core.navigation.state.AppBarState
import com.arshapshap.versati.feature.auth.impl.presentation.authFeatureGraph
import com.arshapshap.versati.feature.charts.impl.presentation.chartsFeatureGraph
import com.arshapshap.versati.feature.imageparsing.impl.presentation.imageParsingFeatureGraph
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrCodesFeatureGraph

@Composable
internal fun MainNavHost(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String,
    appBarConfigure: (AppBarState) -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        authFeatureGraph(navController, appBarConfigure)
        chartsFeatureGraph(navController, appBarConfigure)
        qrCodesFeatureGraph(navController, appBarConfigure)
        imageParsingFeatureGraph(navController, appBarConfigure)
    }
}