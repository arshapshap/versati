package com.arshapshap.versati.feature.imageparsing.api.domain.repository

import android.graphics.Bitmap
import com.arshapshap.versati.feature.imageparsing.api.domain.model.Language
import com.arshapshap.versati.feature.imageparsing.api.domain.model.ParsingResult

interface ImageParsingRepository {

    suspend fun parseImageByUrl(url: String, language: Language): ParsingResult

    suspend fun parseImageBitmap(image: Bitmap, language: Language): ParsingResult

    suspend fun getParsingHistory(): List<ParsingResult>

    suspend fun clearHistory()

    suspend fun getParsingResultById(id: Long): ParsingResult?
}