package com.arshapshap.versati.feature.imageparsing.impl.presentation.parsing

import android.content.Context
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.arshapshap.versati.core.navigation.features.ImageParsingFeature
import com.arshapshap.versati.core.navigation.state.AppBarState
import com.arshapshap.versati.core.utils.showToast
import com.arshapshap.versati.feature.imageparsing.impl.R
import com.arshapshap.versati.feature.imageparsing.impl.presentation.parsing.contract.ParsingSideEffect
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

internal object ParsingScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        id: Long?,
        appBarConfigure: (AppBarState) -> Unit
    ) {
        val viewModel =
            getViewModel<ParsingViewModel>(parameters = { parametersOf(id ?: 0) })
        val state by viewModel.collectAsState()

        val context = LocalContext.current
        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                ParsingSideEffect.AuthorizationError -> context.showToast(R.string.authorization_error)
                ParsingSideEffect.TimeoutError -> context.showToast(R.string.timeout_error)
                ParsingSideEffect.NetworkError -> context.showToast(R.string.network_error)
                ParsingSideEffect.ParsingError -> context.showToast(R.string.parsing_error)
                ParsingSideEffect.NavigateToHistory ->
                    navController.navigate(ImageParsingFeature.ParsingHistory.destination())
            }
        }

        SideEffect {
            appBarConfigure(
                getAppBarState(
                    context = context,
                    onHistoryClick = viewModel::navigateToParsingHistory
                )
            )
        }
        ParsingContent(state = state, viewModel = viewModel)
    }

    private fun getAppBarState(
        context: Context,
        onHistoryClick: () -> Unit = { }
    ) = AppBarState(
        currentRoute = ImageParsingFeature.Parsing.route,
        title = context.getString(R.string.image_parsing),
        actions = {
            IconButton(onClick = onHistoryClick) {
                Icon(
                    painter = painterResource(id = com.arshapshap.versati.core.designsystem.R.drawable.ic_history),
                    contentDescription = stringResource(R.string.open_parsing_history)
                )
            }
        }
    )
}