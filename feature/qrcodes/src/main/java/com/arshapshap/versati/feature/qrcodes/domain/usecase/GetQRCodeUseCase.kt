package com.arshapshap.versati.feature.qrcodes.domain.usecase

import com.arshapshap.versati.feature.qrcodes.domain.model.QRCodeOptions
import com.arshapshap.versati.feature.qrcodes.domain.repository.QRCodesRepository

internal class GetQRCodeUseCase(
    private val repository: QRCodesRepository
) {

    suspend operator fun invoke(options: QRCodeOptions): String {
        return repository.getQRCodeImageUrl(options)
    }
}