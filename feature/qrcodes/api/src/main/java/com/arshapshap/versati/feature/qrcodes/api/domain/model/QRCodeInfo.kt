package com.arshapshap.versati.feature.qrcodes.api.domain.model

data class QRCodeInfo(
    val id: Long,
    val data: String,
    val size: Int,
    val color: Int,
    val backgroundColor: Int,
    val quietZone: Int,
    val format: ImageFormat,
    val imageUrl: String
)