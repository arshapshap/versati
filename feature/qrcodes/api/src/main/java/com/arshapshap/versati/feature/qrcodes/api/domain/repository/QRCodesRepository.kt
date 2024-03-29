package com.arshapshap.versati.feature.qrcodes.api.domain.repository

import com.arshapshap.versati.feature.qrcodes.api.domain.model.QRCodeInfo

interface QRCodesRepository {

    suspend fun createQRCodeImageUrl(options: QRCodeInfo): String

    suspend fun getQRCodesHistory(): List<QRCodeInfo>

    suspend fun getQRCodeInfoById(id: Long): QRCodeInfo?

    suspend fun clearHistory()
}