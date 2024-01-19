package com.arshapshap.versati.feature.charts.impl.presentation.chartshistory

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
import com.arshapshap.versati.core.navigation.ChartsFeature
import com.arshapshap.versati.core.navigation.state.AppBarState
import com.arshapshap.versati.feature.charts.impl.R
import com.arshapshap.versati.feature.charts.impl.presentation.chartshistory.contract.ChartsHistorySideEffect
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect


internal object ChartsHistoryScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        appBarConfigure: (AppBarState) -> Unit
    ) {
        val viewModel =
            getViewModel<ChartsHistoryViewModel>()
        val state by viewModel.collectAsState()

        val context = LocalContext.current
        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is ChartsHistorySideEffect.OpenChart ->
                    navController.navigate(ChartsFeature.ChartGeneration.destination(id = sideEffect.id))
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
        ChartsHistoryContent(state = state, viewModel = viewModel)
    }

    private fun getAppBarState(
        context: Context,
        showClearButton: Boolean,
        onClearClick: () -> Unit
    ) = AppBarState(
        currentRoute = ChartsFeature.ChartsHistory.route,
        title = context.getString(R.string.charts_history),
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