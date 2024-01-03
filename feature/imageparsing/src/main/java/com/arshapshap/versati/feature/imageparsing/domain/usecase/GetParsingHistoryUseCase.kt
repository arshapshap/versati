package com.arshapshap.versati.feature.imageparsing.domain.usecase

import com.arshapshap.versati.feature.imageparsing.domain.model.ImageParsingResult
import com.arshapshap.versati.feature.imageparsing.domain.repository.ImageParsingRepository

internal class GetParsingHistoryUseCase(
    private val repository: ImageParsingRepository
) {

    suspend operator fun invoke(): List<ImageParsingResult> {
        return repository.getParsingHistory()
    }
}