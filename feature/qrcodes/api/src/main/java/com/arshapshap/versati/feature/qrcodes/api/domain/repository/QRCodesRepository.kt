package com.arshapshap.versati.feature.qrcodes.api.domain.repository

import com.arshapshap.versati.feature.qrcodes.api.domain.model.QRCodeInfo

interface QRCodesRepository {

    suspend fun createQRCodeImageUrl(options: QRCodeInfo): String

    suspend fun getRequestHistory(): List<QRCodeInfo>
}