package com.arshapshap.versati.feature.imageparsing.domain.repository

import android.graphics.Bitmap
import com.arshapshap.versati.feature.imageparsing.domain.model.ImageParsingResult
import com.arshapshap.versati.feature.imageparsing.domain.model.Language

internal interface ImageParsingRepository {

    suspend fun parseImageByUrl(url: String, language: Language): ImageParsingResult

    suspend fun parseImageBitmap(image: Bitmap, language: Language): ImageParsingResult
}