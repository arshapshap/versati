package com.arshapshap.versati.feature.qrcodes.impl.presentation.requesthistory

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.arshapshap.versati.core.navigation.QRCodesFeature
import com.arshapshap.versati.core.navigation.state.AppBarState
import com.arshapshap.versati.feature.qrcodes.impl.R
import com.arshapshap.versati.feature.qrcodes.impl.presentation.requesthistory.contract.RequestHistorySideEffect
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect


object RequestHistoryScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        appBarConfigure: (AppBarState) -> Unit
    ) {
        val screenModel = getViewModel<RequestHistoryViewModel>()
        val state by screenModel.collectAsState()

        val context = LocalContext.current
        screenModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is RequestHistorySideEffect.OpenQRCode ->
                    navController.navigate(QRCodesFeature.QRCodeGeneration.destination(id = sideEffect.id))
            }
        }

        SideEffect {
            appBarConfigure(
                getAppBarState(context)
            )
        }
        RequestHistoryContent(state = state, viewModel = screenModel)
    }

    private fun getAppBarState(
        context: Context
    ) = AppBarState(
        title = context.getString(R.string.request_history),
        showArrowBack = true
    )
}