package com.arshapshap.versati

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.arshapshap.versati.core.designsystem.theme.VersatiTheme
import com.arshapshap.versati.core.navigation.QRCodesFeatureScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VersatiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigator(screen = rememberScreen(QRCodesFeatureScreen.QRCodeGeneration(0))) { navigator ->
                        Scaffold(
                            topBar = { TopBar() },
                            content = {
                                Box(
                                    modifier = Modifier
                                        .padding(it)
                                ) {
                                    SlideTransition(navigator)
                                }
                            },
                            bottomBar = { BottomBar() }
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun TopBar() {

    }

    @Composable
    private fun BottomBar() {

    }
}