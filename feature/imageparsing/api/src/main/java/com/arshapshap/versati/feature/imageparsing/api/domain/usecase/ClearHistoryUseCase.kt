package com.arshapshap.versati.feature.imageparsing.api.domain.usecase

import com.arshapshap.versati.feature.imageparsing.api.domain.repository.ImageParsingRepository

class ClearHistoryUseCase(
    private val repository: ImageParsingRepository
) {

    suspend operator fun invoke() {
        return repository.clearHistory()
    }
}