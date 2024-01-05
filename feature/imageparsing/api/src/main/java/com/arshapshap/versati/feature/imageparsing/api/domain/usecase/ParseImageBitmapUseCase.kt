package com.arshapshap.versati.feature.imageparsing.api.domain.usecase

import android.graphics.Bitmap
import com.arshapshap.versati.feature.imageparsing.api.domain.model.Language
import com.arshapshap.versati.feature.imageparsing.api.domain.model.ParsingResult
import com.arshapshap.versati.feature.imageparsing.api.domain.repository.ImageParsingRepository

class ParseImageBitmapUseCase(
    private val repository: ImageParsingRepository
) {

    suspend operator fun invoke(image: Bitmap, language: Language): ParsingResult {
        return repository.parseImageBitmap(image, language)
    }
}