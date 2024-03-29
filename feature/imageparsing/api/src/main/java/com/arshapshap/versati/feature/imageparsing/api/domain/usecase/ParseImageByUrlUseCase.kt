package com.arshapshap.versati.feature.imageparsing.api.domain.usecase

import com.arshapshap.versati.feature.imageparsing.api.domain.model.Language
import com.arshapshap.versati.feature.imageparsing.api.domain.model.ParsingResult
import com.arshapshap.versati.feature.imageparsing.api.domain.repository.ImageParsingRepository

class ParseImageByUrlUseCase(
    private val repository: ImageParsingRepository
) {

    suspend operator fun invoke(url: String, language: Language): ParsingResult {
        return repository.parseImageByUrl(url, language)
    }
}