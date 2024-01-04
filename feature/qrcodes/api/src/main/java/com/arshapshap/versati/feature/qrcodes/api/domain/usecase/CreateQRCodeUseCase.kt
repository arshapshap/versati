package com.arshapshap.versati.feature.qrcodes.api.domain.usecase

import com.arshapshap.versati.feature.qrcodes.api.domain.model.QRCodeOptions
import com.arshapshap.versati.feature.qrcodes.api.domain.repository.QRCodesRepository

class CreateQRCodeUseCase(
    private val repository: QRCodesRepository
) {

    suspend operator fun invoke(options: QRCodeOptions): String {
        return repository.createQRCodeImageUrl(options)
    }
}