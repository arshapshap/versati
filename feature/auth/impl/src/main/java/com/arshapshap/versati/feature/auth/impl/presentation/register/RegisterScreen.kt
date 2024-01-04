package com.arshapshap.versati.feature.auth.impl.presentation.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.koin.getScreenModel
import org.orbitmvi.orbit.compose.collectAsState

class RegisterScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<RegisterScreenModel>()
        val state by screenModel.collectAsState()
        RegisterContent(state = state, screenModel = screenModel)
    }
}