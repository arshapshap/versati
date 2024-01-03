package com.arshapshap.versati.feature.imageparsing.domain.repository

import android.graphics.Bitmap
import com.arshapshap.versati.feature.imageparsing.domain.model.ParsingResult
import com.arshapshap.versati.feature.imageparsing.domain.model.Language

internal interface ImageParsingRepository {

    suspend fun parseImageByUrl(url: String, language: Language): ParsingResult

    suspend fun parseImageBitmap(image: Bitmap, language: Language): ParsingResult

    suspend fun getParsingHistory(): List<ParsingResult>

    suspend fun getParsingInfoById(id: Long): ParsingResult
}