package com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration

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
import com.arshapshap.versati.core.navigation.QRCodesFeature
import com.arshapshap.versati.core.navigation.state.AppBarState
import com.arshapshap.versati.core.utils.ImageLoadingHelper
import com.arshapshap.versati.core.utils.SharingHelper
import com.arshapshap.versati.core.utils.StorageHelper
import com.arshapshap.versati.feature.qrcodes.impl.R
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.contract.QRCodeGenerationSideEffect
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect


internal object QRCodeGenerationScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        id: Long?,
        appBarConfigure: (AppBarState) -> Unit
    ) {
        val viewModel =
            getViewModel<QRCodeGenerationViewModel>(parameters = { parametersOf(id ?: 0) })
        val state by viewModel.collectAsState()

        val context = LocalContext.current
        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is QRCodeGenerationSideEffect.ShareQRCode ->
                    shareQRCode(
                        context,
                        sideEffect.imageUrl,
                        sideEffect.imageFormat.name.lowercase()
                    )

                QRCodeGenerationSideEffect.NavigateToRequestHistory ->
                    navController.navigate(QRCodesFeature.RequestHistory.destination())
            }
        }

        SideEffect {
            appBarConfigure(
                getAppBarState(
                    context,
                    viewModel::navigateToRequestHistory
                )
            )
        }
        QRCodeGenerationContent(state = state, viewModel = viewModel)
    }

    private fun getAppBarState(
        context: Context,
        onHistoryClick: () -> Unit
    ) = AppBarState(
        currentRoute = QRCodesFeature.QRCodeGeneration.route,
        title = context.getString(R.string.qr_code),
        actions = {
            IconButton(onClick = onHistoryClick) {
                Icon(
                    painter = painterResource(id = com.arshapshap.versati.core.designsystem.R.drawable.ic_history),
                    contentDescription = stringResource(R.string.open_request_history)
                )
            }
        }
    )

    private suspend fun shareQRCode(context: Context, imageUrl: String, format: String) {
        val bitmap = ImageLoadingHelper.loadImageAsBitmap(context, imageUrl, format)
        val uri = StorageHelper.getImageUriFromBitmap(context, bitmap, format)
        SharingHelper.shareImage(context, uri)
    }
}