package com.arshapshap.versati.feature.qrcodes.api.domain.usecase

import com.arshapshap.versati.feature.qrcodes.api.domain.model.QRCodeInfo
import com.arshapshap.versati.feature.qrcodes.api.domain.repository.QRCodesRepository

class GetRequestHistoryUseCase(
    private val repository: QRCodesRepository
) {

    suspend operator fun invoke(): List<QRCodeInfo> {
        return repository.getRequestHistory()
    }
}