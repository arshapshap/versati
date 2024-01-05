package com.arshapshap.versati.feature.qrcodes.impl.presentation.requesthistory

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
                getAppBarState(
                    context = context,
                    showClearButton = state.history.isNotEmpty(),
                    onClearClick = screenModel::clearHistoryUnconfirmed
                )
            )
        }
        RequestHistoryContent(state = state, viewModel = screenModel)
    }

    private fun getAppBarState(
        context: Context,
        showClearButton: Boolean,
        onClearClick: () -> Unit
    ) = AppBarState(
        title = context.getString(R.string.request_history),
        showArrowBack = true,
        actions = {
            if (showClearButton)
                IconButton(onClick = onClearClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(R.string.clear_history)
                    )
                }
        }
    )
}