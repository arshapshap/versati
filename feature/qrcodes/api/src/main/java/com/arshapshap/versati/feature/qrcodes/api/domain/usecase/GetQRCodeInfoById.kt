package com.arshapshap.versati.feature.qrcodes.api.domain.usecase

import com.arshapshap.versati.feature.qrcodes.api.domain.model.QRCodeInfo
import com.arshapshap.versati.feature.qrcodes.api.domain.repository.QRCodesRepository

class GetQRCodeInfoById(
    private val repository: QRCodesRepository
) {

    suspend operator fun invoke(id: Long): QRCodeInfo? {
        return repository.getQRCodeInfoById(id)
    }
}