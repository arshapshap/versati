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
import com.arshapshap.versati.core.navigation.AuthFeature
import com.arshapshap.versati.core.navigation.QRCodesFeature
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
import org.koin.androidx.compose.get

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    private lateinit var analytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureFirebase()

        setContent {
            val navController = rememberNavController()

            val repository = get<LastOpenedFeatureRepository>()
            navController.addOnDestinationChangedListener { _, destination, _ ->
                destination.route?.let { route ->
                    if (route.startsWith(AuthFeature.featureRoute)) return@addOnDestinationChangedListener
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
                                onProfileClick = {
                                    navController.navigate(AuthFeature.Account.destination()) {
                                        launchSingleTop = true
                                    }
                                }
                            )
                        },
                        content = {
                            MainNavHost(
                                modifier = Modifier.padding(it),
                                navController = navController,
                                startDestination = repository.getLastOpenedFeature()
                                    ?: QRCodesFeature.featureRoute
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