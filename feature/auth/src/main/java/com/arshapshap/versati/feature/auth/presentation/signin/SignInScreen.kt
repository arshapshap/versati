package com.arshapshap.versati.feature.auth.presentation.signin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.koin.getScreenModel
import org.orbitmvi.orbit.compose.collectAsState

class SignInScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<SignInScreenModel>()
        val state by screenModel.collectAsState()
        SignInContent(state = state, screenModel = screenModel)
    }
}