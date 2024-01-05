package com.arshapshap.versati.feature.qrcodes.api.domain.usecase

import com.arshapshap.versati.feature.qrcodes.api.domain.repository.QRCodesRepository

class ClearHistoryUseCase(
    private val repository: QRCodesRepository
) {

    suspend operator fun invoke() {
        return repository.clearHistory()
    }
}