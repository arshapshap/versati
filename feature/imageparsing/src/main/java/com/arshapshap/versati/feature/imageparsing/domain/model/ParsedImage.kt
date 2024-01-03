package com.arshapshap.versati.feature.imageparsing.domain.model

internal data class ParsedImage(
    val fileParseExitCode: Int = 1,
    val parsedText: String,
    val errorMessage: String = "",
    val errorDetails: String = ""
)