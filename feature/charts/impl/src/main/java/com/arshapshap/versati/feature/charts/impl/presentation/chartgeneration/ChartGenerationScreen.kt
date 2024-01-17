package com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration

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
import com.arshapshap.versati.core.navigation.ChartsFeature
import com.arshapshap.versati.core.navigation.state.AppBarState
import com.arshapshap.versati.core.utils.ImageLoadingHelper
import com.arshapshap.versati.core.utils.SharingHelper
import com.arshapshap.versati.core.utils.StorageHelper
import com.arshapshap.versati.feature.charts.impl.R
import com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.contract.ChartGenerationSideEffect
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect


internal object ChartGenerationScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        id: Long?,
        appBarConfigure: (AppBarState) -> Unit
    ) {
        val viewModel =
            getViewModel<ChartGenerationViewModel>(parameters = { parametersOf(id ?: 0) })
        val state by viewModel.collectAsState()

        val context = LocalContext.current
        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is ChartGenerationSideEffect.ShareChart ->
                    shareQRCode(
                        context,
                        sideEffect.imageUrl
                    )

                ChartGenerationSideEffect.NavigateToChartsHistory ->
                    navController.navigate(ChartsFeature.ChartsHistory.destination())
            }
        }

        SideEffect {
            appBarConfigure(
                getAppBarState(
                    context,
                    viewModel::navigateToChartsHistory
                )
            )
        }
        QRCodeGenerationContent(state = state, viewModel = viewModel)
    }

    private fun getAppBarState(
        context: Context,
        onHistoryClick: () -> Unit
    ) = AppBarState(
        currentRoute = ChartsFeature.ChartGeneration.route,
        title = context.getString(R.string.charts),
        actions = {
            IconButton(onClick = onHistoryClick) {
                Icon(
                    painter = painterResource(id = com.arshapshap.versati.core.designsystem.R.drawable.ic_history),
                    contentDescription = stringResource(R.string.open_charts_history)
                )
            }
        }
    )

    private suspend fun shareQRCode(context: Context, imageUrl: String) {
        val bitmap = ImageLoadingHelper.loadImageAsBitmap(context, imageUrl)
        val uri = StorageHelper.getImageUriFromBitmap(context, bitmap)
        SharingHelper.shareImage(context, uri)
    }
}