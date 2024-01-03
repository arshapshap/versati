package com.arshapshap.versati.feature.imageparsing.domain.model

internal data class ImageParsingResult(
    val id: Long,
    val parsedResults: List<ParsedImage>,
    val ocrExitCode: Int,
    val isErroredOnProcessing: Boolean,
    val searchablePDFURL: String
)