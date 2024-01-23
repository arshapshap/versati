package com.arshapshap.versati.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.arshapshap.versati.core.designsystem.theme.VersatiTheme
import com.arshapshap.versati.core.navigation.features.ChartsFeature
import com.arshapshap.versati.core.navigation.features.ImageParsingFeature
import com.arshapshap.versati.core.navigation.features.QRCodesFeature
import com.arshapshap.versati.core.navigation.features.SettingsFeature
import com.arshapshap.versati.core.navigation.state.AppBarState
import com.arshapshap.versati.data.LastOpenedFeatureRepository
import com.arshapshap.versati.presentation.elements.BottomBar
import com.arshapshap.versati.presentation.elements.TopBar
import com.arshapshap.versati.presentation.navigation.MainNavHost
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import org.koin.android.ext.android.inject

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    private lateinit var analytics: FirebaseAnalytics
    private val repository: LastOpenedFeatureRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureFirebase()

        val startDestination = repository.getLastOpenedFeature() ?: QRCodesFeature.featureRoute
        setContent {
            val navController = rememberNavController()
            repository.setLastOpenedFeature(QRCodesFeature.featureRoute)
            navController.addOnDestinationChangedListener { _, destination, _ ->
                destination.route?.let { route ->
                    if (isSaveableFeatureRoute(route))
                        repository.setLastOpenedFeature(route.takeWhile { it != '/' })
                }
            }

            VersatiTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
                    var appBarState by remember { mutableStateOf(AppBarState()) }
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .nestedScroll(scrollBehavior.nestedScrollConnection),
                        topBar = {
                            TopBar(
                                scrollBehavior = scrollBehavior,
                                state = appBarState,
                                onSettingsClick = {
                                    navController.navigate(SettingsFeature.Settings.destination()) {
                                        launchSingleTop = true
                                    }
                                }
                            )
                        },
                        content = {
                            MainNavHost(
                                modifier = Modifier.padding(it),
                                navController = navController,
                                startDestination = startDestination
                            ) { state ->
                                if (navController.currentDestination?.route == state.currentRoute)
                                    appBarState = state
                            }
                        },
                        bottomBar = {
                            if (appBarState.showBottomBar)
                                BottomBar(
                                    navController = navController
                                )
                        }
                    )
                }
            }
        }
    }

    private fun isSaveableFeatureRoute(route: String): Boolean =
        route.startsWith(ChartsFeature.featureRoute)
                || route.startsWith(ImageParsingFeature.featureRoute)
                || route.startsWith(QRCodesFeature.featureRoute)

    private fun configureFirebase() {
        analytics = Firebase.analytics
        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(com.arshapshap.versati.core.firebase.R.xml.remote_config_defaults)
        remoteConfig.fetchAndActivate()
    }

    @Preview
    @Composable
    private fun Preview() {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
            topBar = { TopBar(scrollBehavior) },
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {

                }
            },
            bottomBar = {
                //BottomBar()
            }
        )
    }
}