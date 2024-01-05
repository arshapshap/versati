package com.arshapshap.versati.feature.imageparsing.api.domain.model

data class ParsingResult(
    val id: Long,
    val parsedResults: List<ParsedImage>,
    val ocrExitCode: Int = 1,
    val isErroredOnProcessing: Boolean = false,
    val searchablePDFURL: String
)