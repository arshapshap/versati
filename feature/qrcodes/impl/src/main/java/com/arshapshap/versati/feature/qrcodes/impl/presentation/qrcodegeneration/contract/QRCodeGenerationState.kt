package com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.contract

import android.graphics.Bitmap
import com.arshapshap.versati.feature.qrcodes.api.domain.model.ImageFormat

internal data class QRCodeGenerationState(
    val data: String = "",
    val showDataFieldError: Boolean = false,
    val advancedOptionsExpanded: Boolean = false,
    val size: Int? = 200,
    val qrCodeColorString: String = "000000",
    val qrCodeColor: Int? = 0x000000,
    val showColorFieldError: Boolean = false,
    val backgroundColorString: String = "ffffff",
    val backgroundColor: Int? = 0xFFFFFF,
    val showBackgroundColorFieldError: Boolean = false,
    val quietZone: Int? = 1,
    val format: ImageFormat = ImageFormat.PNG,
    val qrCodeImageUrl: String = "",
    val loading: Boolean = false,
    val success: Boolean = false,
    val bitmap: Bitmap? = null,
    val optionsChanged: Boolean = true
)