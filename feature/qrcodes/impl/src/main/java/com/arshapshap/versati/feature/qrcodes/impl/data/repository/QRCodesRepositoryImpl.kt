package com.arshapshap.versati.feature.qrcodes.impl.data.repository

import com.arshapshap.versati.core.database.dao.qrcodesfeature.QRCodeRequestDao
import com.arshapshap.versati.feature.qrcodes.api.domain.model.QRCodeInfo
import com.arshapshap.versati.feature.qrcodes.api.domain.repository.QRCodesRepository
import com.arshapshap.versati.feature.qrcodes.impl.data.mapper.QRCodesMapper

private const val MAX_HISTORY_SIZE = 20

internal class QRCodesRepositoryImpl(
    private val dao: QRCodeRequestDao,
    private val mapper: QRCodesMapper,
) : QRCodesRepository {

    override suspend fun createQRCodeImageUrl(options: QRCodeInfo): String {
        val imageUrl = mapper.createImageUrl(options)
        dao.add(mapper.mapToLocal(options, 0, imageUrl))
        if (dao.getCount() > MAX_HISTORY_SIZE)
            dao.deleteOldest()
        return imageUrl
    }

    override suspend fun getRequestHistory(): List<QRCodeInfo> {
        return dao.getAll().map { mapper.mapFromLocal(it) }
    }
}