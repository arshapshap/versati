package com.arshapshap.versati.feature.imageparsing.api.domain.usecase

import com.arshapshap.versati.feature.imageparsing.api.domain.model.ParsingResult
import com.arshapshap.versati.feature.imageparsing.api.domain.repository.ImageParsingRepository

class GetParsingHistoryUseCase(
    private val repository: ImageParsingRepository
) {

    suspend operator fun invoke(): List<ParsingResult> {
        return repository.getParsingHistory()
    }
}