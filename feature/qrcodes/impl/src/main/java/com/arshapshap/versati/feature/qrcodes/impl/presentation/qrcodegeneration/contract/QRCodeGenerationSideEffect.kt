package com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.contract

import android.graphics.Bitmap
import com.arshapshap.versati.feature.qrcodes.api.domain.model.ImageFormat

internal sealed interface QRCodeGenerationSideEffect {
    data class ShareQRCode(
        val bitmap: Bitmap?,
        val imageFormat: ImageFormat
    ) : QRCodeGenerationSideEffect

    data object NavigateToQRCodesHistory : QRCodeGenerationSideEffect
}