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
import com.arshapshap.versati.core.navigation.AppBarState
import com.arshapshap.versati.core.navigation.AuthFeature
import com.arshapshap.versati.core.utils.ImageLoadingHelper
import com.arshapshap.versati.core.utils.SharingHelper
import com.arshapshap.versati.core.utils.StorageHelper
import com.arshapshap.versati.feature.qrcodes.impl.R
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.contract.QRCodeGenerationSideEffect
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect


object QRCodeGenerationScreen {

    @Composable
    fun Content(
        navController: NavHostController,
        id: Long?,
        appBarConfigure: (AppBarState) -> Unit
    ) {
        val screenModel = getViewModel<QRCodeGenerationViewModel>()
        val state by screenModel.collectAsState()

        val context = LocalContext.current
        screenModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is QRCodeGenerationSideEffect.ShareQRCode ->
                    shareQRCode(
                        context,
                        sideEffect.imageUrl,
                        sideEffect.imageFormat.name.lowercase()
                    )

                QRCodeGenerationSideEffect.NavigateToRequestHistory ->
                    navController.navigate(AuthFeature.SignIn.destination())
            }
        }

        SideEffect {
            appBarConfigure(
                getAppBarState(
                    context,
                    screenModel::navigateToRequestHistory
                )
            ) // TODO: сделать переход к истории
        }
        QRCodeGenerationContent(state = state, viewModel = screenModel)
    }

    private fun getAppBarState(
        context: Context,
        onHistoryClick: () -> Unit
    ) = AppBarState(
        title = context.getString(R.string.qr_code),
        actions = {
            IconButton(onClick = onHistoryClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_history),
                    contentDescription = stringResource(R.string.open_requests_history)
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