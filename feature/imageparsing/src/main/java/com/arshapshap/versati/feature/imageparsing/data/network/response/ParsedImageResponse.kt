package com.arshapshap.versati.feature.imageparsing.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ParsedImageResponse(
    @SerialName("FileParseExitCode")
    val fileParseExitCode: Int,
    @SerialName("ParsedText")
    val parsedText: String,
    @SerialName("ErrorMessage")
    val errorMessage: String,
    @SerialName("ErrorDetails")
    val errorDetails: String
)