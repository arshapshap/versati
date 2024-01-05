package com.arshapshap.versati

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arshapshap.versati.core.designsystem.theme.VersatiTheme
import com.arshapshap.versati.core.navigation.AuthFeature
import com.arshapshap.versati.core.navigation.QRCodesFeature
import com.arshapshap.versati.feature.auth.impl.presentation.register.RegisterScreen
import com.arshapshap.versati.feature.auth.impl.presentation.signin.SignInScreen
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.QRCodeGenerationScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            VersatiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = { },
                        content = {
                            MainNavHost(
                                modifier = Modifier.padding(it),
                                navController = navController,
                                startRoute = QRCodesFeature.QRCodeGeneration.route
                            )
                        },
                        bottomBar = { BottomBar() }
                    )
                }
            }
        }
    }

    @Composable
    private fun MainNavHost(
        modifier: Modifier,
        navController: NavHostController,
        startRoute: String
    ) {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startRoute
        ) {
            composable(AuthFeature.SignIn.route) { SignInScreen.Content(navController = navController) }
            composable(AuthFeature.Register.route) { RegisterScreen.Content(navController = navController) }

            composable(
                route = QRCodesFeature.QRCodeGeneration.route,
                arguments = QRCodesFeature.QRCodeGeneration.arguments
            ) {
                QRCodeGenerationScreen.Content(
                    navController = navController,
                    id = it.arguments?.getLong(QRCodesFeature.QRCodeGeneration.idArgument)
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun TopBar() {
        TopAppBar(
            title = {
                Text(
                    "Simple TopAppBar",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Localized description"
                    )
                }
            },
            actions = {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Localized description"
                    )
                }
            }
        )
    }

    @Composable
    private fun BottomBar() {

    }

    @Preview
    @Composable
    private fun Preview() {
        Scaffold(
            topBar = { TopBar() },
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