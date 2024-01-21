package com.arshapshap.versati.feature.imageparsing.impl.presentation.history

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
import com.arshapshap.versati.core.navigation.features.ImageParsingFeature
import com.arshapshap.versati.core.navigation.state.AppBarState
import com.arshapshap.versati.feature.imageparsing.impl.R
import com.arshapshap.versati.feature.imageparsing.impl.presentation.history.contract.ParsingHistorySideEffect
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

internal object ParsingHistoryScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        appBarConfigure: (AppBarState) -> Unit
    ) {
        val viewModel = getViewModel<ParsingHistoryViewModel>()
        val state by viewModel.collectAsState()

        val context = LocalContext.current
        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is ParsingHistorySideEffect.OpenParsingResult ->
                    navController.navigate(ImageParsingFeature.Parsing.destination(id = sideEffect.id))
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
        ParsingHistoryContent(state = state, viewModel = viewModel)
    }

    private fun getAppBarState(
        context: Context,
        showClearButton: Boolean,
        onClearClick: () -> Unit
    ) = AppBarState(
        currentRoute = ImageParsingFeature.ParsingHistory.route,
        title = context.getString(R.string.parsing_history),
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