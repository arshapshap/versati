package com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.contract

import com.arshapshap.versati.feature.qrcodes.api.domain.model.ImageFormat

internal sealed interface QRCodeGenerationSideEffect {
    data class ShareQRCode(val imageUrl: String, val imageFormat: ImageFormat) :
        QRCodeGenerationSideEffect
}