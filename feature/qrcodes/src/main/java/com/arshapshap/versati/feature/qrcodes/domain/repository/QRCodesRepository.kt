package com.arshapshap.versati.feature.qrcodes.domain.repository

import com.arshapshap.versati.feature.qrcodes.domain.model.QRCodeOptions

internal interface QRCodesRepository {

    suspend fun getQRCodeImageUrl(options: QRCodeOptions): String
}