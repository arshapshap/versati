package com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.koin.getScreenModel
import com.arshapshap.versati.core.utils.ImageLoadingHelper
import com.arshapshap.versati.core.utils.SharingHelper
import com.arshapshap.versati.core.utils.StorageHelper
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.contract.QRCodeGenerationSideEffect
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect


internal class QRCodeGenerationScreen(
    private val id: Long
) : AndroidScreen() {

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<QRCodeGenerationScreenModel>()
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
            }
        }

        QRCodeGenerationContent(state = state, screenModel = screenModel)
    }

    private suspend fun shareQRCode(context: Context, imageUrl: String, format: String) {
        val bitmap = ImageLoadingHelper.loadImageAsBitmap(context, imageUrl, format)
        val uri = StorageHelper.getImageUriFromBitmap(context, bitmap, format)
        SharingHelper.shareImage(context, uri)
    }
}