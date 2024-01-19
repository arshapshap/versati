package com.arshapshap.versati.presentation.elements

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.arshapshap.versati.core.navigation.ChartsFeature
import com.arshapshap.versati.core.navigation.ImageParsingFeature
import com.arshapshap.versati.core.navigation.QRCodesFeature

@Composable
internal fun BottomBar(
    navController: NavHostController
) {
    val screens = listOf(
        Screen.QRCodes,
        Screen.ImageParsing,
        Screen.Charts
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
        modifier = Modifier
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        screens.forEach { screen ->
            BottomNavigationItem(
                selected = currentRoute?.startsWith(screen.route) ?: false,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = screen.iconRes),
                        contentDescription = stringResource(id = screen.labelRes)
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = screen.labelRes),
                        style = MaterialTheme.typography.labelLarge
                    )
                },
                alwaysShowLabel = false
            )
        }
    }
}

private sealed class Screen(
    val route: String,
    @StringRes val labelRes: Int,
    @DrawableRes val iconRes: Int
) {

    data object Charts : Screen(
        route = ChartsFeature.featureRoute,
        labelRes = com.arshapshap.versati.feature.charts.impl.R.string.charts,
        iconRes = com.arshapshap.versati.feature.charts.impl.R.drawable.ic_create_chart
    )

    data object ImageParsing : Screen(
        route = ImageParsingFeature.featureRoute,
        labelRes = com.arshapshap.versati.feature.imageparsing.impl.R.string.image_parsing,
        iconRes = com.arshapshap.versati.feature.imageparsing.impl.R.drawable.ic_image_parsing
    )

    data object QRCodes : Screen(
        route = QRCodesFeature.featureRoute,
        labelRes = com.arshapshap.versati.feature.qrcodes.impl.R.string.qr_codes,
        iconRes = com.arshapshap.versati.feature.qrcodes.impl.R.drawable.ic_create_qr_code
    )
}