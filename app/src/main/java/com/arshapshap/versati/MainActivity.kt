package com.arshapshap.versati

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arshapshap.versati.core.designsystem.theme.VersatiTheme
import com.arshapshap.versati.core.navigation.AuthFeature
import com.arshapshap.versati.core.navigation.QRCodesFeature
import com.arshapshap.versati.core.navigation.state.AppBarState
import com.arshapshap.versati.feature.auth.impl.presentation.register.RegisterScreen
import com.arshapshap.versati.feature.auth.impl.presentation.signin.SignInScreen
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.QRCodeGenerationScreen
import com.arshapshap.versati.feature.qrcodes.impl.presentation.requesthistory.RequestHistoryScreen

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

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
                                appBarState = appBarState
                            )
                        },
                        content = {
                            MainNavHost(
                                modifier = Modifier.padding(it),
                                navController = navController,
                                startRoute = QRCodesFeature.QRCodeGeneration.route
                            ) { state ->
                                appBarState = state
                            }
                        },
                        bottomBar = {
                            BottomBar()
                        }
                    )
                }
            }
        }
    }

    @Composable
    private fun TopBar(
        scrollBehavior: TopAppBarScrollBehavior,
        appBarState: AppBarState = AppBarState()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = appBarState.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {
                if (appBarState.showArrowBack) {
                    val backPressDispatcher = LocalOnBackPressedDispatcherOwner.current
                    IconButton(
                        onClick = { backPressDispatcher?.onBackPressedDispatcher?.onBackPressed() },
                        content = {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "arrowBack"
                            )
                        }
                    )
                }
            },
            actions = {
                appBarState.actions?.invoke(this)
            },
            scrollBehavior = scrollBehavior
        )
    }

    @Composable
    private fun MainNavHost(
        modifier: Modifier,
        navController: NavHostController,
        startRoute: String,
        appBarConfigure: (AppBarState) -> Unit
    ) {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startRoute
        ) {
            composable(
                route = AuthFeature.SignIn.route
            ) {
                SignInScreen.Content(
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
    }

    @Composable
    private fun BottomBar() {

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
            bottomBar = { BottomBar() }
        )
    }
}