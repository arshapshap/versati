package com.arshapshap.versati.feature.imageparsing.data.database

import com.arshapshap.versati.feature.imageparsing.domain.model.ImageParsingResult

internal abstract class ImageParsingDao {

    abstract suspend fun saveParsingResult(parsingResult: ImageParsingResult): Long

    abstract suspend fun getParsingHistory(): List<ImageParsingResult>
}