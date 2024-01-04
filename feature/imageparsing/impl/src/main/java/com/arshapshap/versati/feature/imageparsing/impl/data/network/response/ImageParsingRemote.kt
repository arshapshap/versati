package com.arshapshap.versati.feature.imageparsing.impl.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ImageParsingRemote(
    @SerialName("ParsedResults")
    val parsedResults: List<ParsedImageRemote>,
    @SerialName("OCRExitCode")
    val ocrExitCode: Int,
    @SerialName("IsErroredOnProcessing")
    val isErroredOnProcessing: Boolean,
    @SerialName("SearchablePDFURL")
    val searchablePDFURL: String
)