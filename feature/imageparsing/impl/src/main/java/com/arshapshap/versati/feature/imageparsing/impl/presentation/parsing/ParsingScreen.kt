package com.arshapshap.versati.feature.imageparsing.impl.presentation.parsing

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.arshapshap.versati.core.navigation.ImageParsingFeature
import com.arshapshap.versati.core.navigation.state.AppBarState
import com.arshapshap.versati.core.utils.showToast
import com.arshapshap.versati.feature.imageparsing.impl.R
import com.arshapshap.versati.feature.imageparsing.impl.presentation.parsing.contract.ParsingSideEffect
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

object ParsingScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        id: Long?,
        appBarConfigure: (AppBarState) -> Unit
    ) {
        val screenModel = getViewModel<ParsingViewModel>()
        val state by screenModel.collectAsState()

        val context = LocalContext.current
        screenModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                ParsingSideEffect.AuthorizationError -> context.showToast(R.string.authorization_error)
                ParsingSideEffect.TimeoutError -> context.showToast(R.string.timeout_error)
                ParsingSideEffect.NetworkError -> context.showToast(R.string.network_error)
                ParsingSideEffect.ParsingError -> context.showToast(R.string.parsing_error)
            }
        }

        SideEffect {
            appBarConfigure(
                getAppBarState(
                    context = context
                )
            )
        }
        ParsingContent(state = state, viewModel = screenModel)
    }

    private fun getAppBarState(
        context: Context,
        onHistoryClick: () -> Unit = { }
    ) = AppBarState(
        currentRoute = ImageParsingFeature.Parsing.route,
        title = context.getString(R.string.image_parsing),
        actions = {
//            IconButton(onClick = onHistoryClick) {
//                Icon(
//                    painter = painterResource(id = com.arshapshap.versati.core.designsystem.R.drawable.ic_history),
//                    contentDescription = stringResource(R.string.open_parsing_history)
//                )
//            }
        }
    )
}