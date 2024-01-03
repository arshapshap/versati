package com.arshapshap.versati.feature.imageparsing.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ImageParsingResponse(
    @SerialName("ParsedResults")
    val parsedResults: List<ParsedImageResponse>,
    @SerialName("OCRExitCode")
    val ocrExitCode: Int,
    @SerialName("IsErroredOnProcessing")
    val isErroredOnProcessing: Boolean,
    @SerialName("SearchablePDFURL")
    val searchablePDFURL: String
)