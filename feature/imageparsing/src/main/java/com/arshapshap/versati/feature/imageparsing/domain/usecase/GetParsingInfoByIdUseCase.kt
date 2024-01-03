package com.arshapshap.versati.feature.imageparsing.domain.usecase

import com.arshapshap.versati.feature.imageparsing.domain.model.ImageParsingResult
import com.arshapshap.versati.feature.imageparsing.domain.repository.ImageParsingRepository

internal class GetParsingInfoByIdUseCase(
    private val repository: ImageParsingRepository
) {

    suspend operator fun invoke(id: Long): ImageParsingResult {
        return repository.getParsingInfoById(id)
    }
}