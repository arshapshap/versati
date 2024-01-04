package com.arshapshap.versati.feature.qrcodes.api.domain.repository

import com.arshapshap.versati.feature.qrcodes.api.domain.model.QRCodeOptions

interface QRCodesRepository {

    suspend fun createQRCodeImageUrl(options: QRCodeOptions): String

    suspend fun getRequestHistory(): List<QRCodeOptions>
}