package com.arshapshap.versati.feature.imageparsing.domain.model

internal data class ParsedImage(
    val textOrientation: String,
    val fileParseExitCode: Int,
    val parsedText: String,
    val errorMessage: String,
    val errorDetails: String
)