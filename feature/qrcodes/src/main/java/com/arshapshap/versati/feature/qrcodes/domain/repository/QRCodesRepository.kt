package com.arshapshap.versati.feature.qrcodes.domain.repository

import com.arshapshap.versati.feature.qrcodes.domain.model.QRCodeOptions

internal interface QRCodesRepository {

    suspend fun createQRCodeImageUrl(options: QRCodeOptions): String

    suspend fun getRequestHistory(): List<QRCodeOptions>
}