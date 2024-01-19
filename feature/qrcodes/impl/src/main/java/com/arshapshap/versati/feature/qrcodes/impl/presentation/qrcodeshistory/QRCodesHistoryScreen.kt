package com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodeshistory

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
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodeshistory.contract.QRCodesHistorySideEffect
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect


internal object QRCodesHistoryScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        appBarConfigure: (AppBarState) -> Unit
    ) {
        val viewModel = getViewModel<QRCodesHistoryViewModel>()
        val state by viewModel.collectAsState()

        val context = LocalContext.current
        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is QRCodesHistorySideEffect.OpenQRCode ->
                    navController.navigate(QRCodesFeature.QRCodeGeneration.destination(id = sideEffect.id))
            }
        }

        SideEffect {
            appBarConfigure(
                getAppBarState(
                    context = context,
                    showClearButton = state.history.isNotEmpty(),
                    onClearClick = viewModel::clearHistoryUnconfirmed
                )
            )
        }
        QRCodesHistoryContent(state = state, viewModel = viewModel)
    }

    private fun getAppBarState(
        context: Context,
        showClearButton: Boolean,
        onClearClick: () -> Unit
    ) = AppBarState(
        currentRoute = QRCodesFeature.QRCodesHistory.route,
        title = context.getString(R.string.qrcodes_history),
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