package com.arshapshap.versati.feature.imageparsing.domain.usecase

import com.arshapshap.versati.feature.imageparsing.domain.model.ImageParsingResult
import com.arshapshap.versati.feature.imageparsing.domain.model.Language
import com.arshapshap.versati.feature.imageparsing.domain.repository.ImageParsingRepository

internal class ParseImageByUrlUseCase(
    private val repository: ImageParsingRepository
) {

    suspend operator fun invoke(url: String, language: Language): ImageParsingResult {
        return repository.parseImageByUrl(url, language)
    }
}