package com.arshapshap.versati.feature.imageparsing.api.domain.model

data class ParsedImage(
    val fileParseExitCode: Int = 1,
    val parsedText: String,
    val errorMessage: String = "",
    val errorDetails: String = ""
)