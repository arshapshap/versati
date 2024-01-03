package com.arshapshap.versati.feature.imageparsing.domain.usecase

import android.graphics.Bitmap
import com.arshapshap.versati.feature.imageparsing.domain.model.ImageParsingResult
import com.arshapshap.versati.feature.imageparsing.domain.model.Language
import com.arshapshap.versati.feature.imageparsing.domain.repository.ImageParsingRepository

internal class ParseImageBitmapUseCase(
    private val repository: ImageParsingRepository
) {

    suspend operator fun invoke(image: Bitmap, language: Language): ImageParsingResult {
        return repository.parseImageBitmap(image, language)
    }
}