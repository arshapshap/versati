package com.arshapshap.versati.feature.qrcodes.domain.usecase

import com.arshapshap.versati.feature.qrcodes.domain.model.QRCodeOptions
import com.arshapshap.versati.feature.qrcodes.domain.repository.QRCodesRepository

internal class GetRequestHistoryUseCase (
    private val repository: QRCodesRepository
) {

    suspend operator fun invoke(): List<QRCodeOptions> {
        return repository.getRequestHistory()
    }
}