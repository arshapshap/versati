package com.arshapshap.versati.feature.qrcodes.data.repository

import com.arshapshap.versati.feature.qrcodes.data.mapper.QRCodesMapper
import com.arshapshap.versati.feature.qrcodes.domain.model.QRCodeOptions
import com.arshapshap.versati.feature.qrcodes.domain.repository.QRCodesRepository

internal class QRCodesRepositoryImpl(
    private val mapper: QRCodesMapper
) : QRCodesRepository {

    override suspend fun createQRCodeImageUrl(options: QRCodeOptions): String {
        return mapper.createImageUrl(options)
    }

    override suspend fun getRequestHistory(): List<QRCodeOptions> {
        TODO("Not yet implemented")
    }
}