package com.arshapshap.versati.feature.qrcodes.domain.model

internal data class QRCodeOptions(
    val data: String,
    val size: Int,
    val color: Long,
    val backgroundColor: Long,
    val quietZone: Int,
    val format: ImageFormat
)

internal enum class ImageFormat {
    PNG,
    GIF,
    JPEG,
    JPG,
    SVG,
    EPS
}