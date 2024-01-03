package com.arshapshap.versati.feature.imageparsing.domain.usecase

import com.arshapshap.versati.feature.imageparsing.domain.model.ParsingResult
import com.arshapshap.versati.feature.imageparsing.domain.repository.ImageParsingRepository

internal class GetParsingResultByIdUseCase(
    private val repository: ImageParsingRepository
) {

    suspend operator fun invoke(id: Long): ParsingResult {
        return repository.getParsingInfoById(id)
    }
}