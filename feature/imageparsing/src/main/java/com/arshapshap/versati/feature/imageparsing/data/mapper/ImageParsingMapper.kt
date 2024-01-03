package com.arshapshap.versati.feature.imageparsing.data.mapper

import com.arshapshap.versati.feature.imageparsing.data.network.response.ImageParsingResponse
import com.arshapshap.versati.feature.imageparsing.data.network.response.ParsedImageResponse
import com.arshapshap.versati.feature.imageparsing.domain.model.ImageParsingResult
import com.arshapshap.versati.feature.imageparsing.domain.model.Language
import com.arshapshap.versati.feature.imageparsing.domain.model.ParsedImage
import okhttp3.MultipartBody
import okhttp3.RequestBody

internal class ImageParsingMapper {

    fun mapToRequestBody(url: String, language: Language): RequestBody {
        return MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("url", url)
            .addFormDataPart("language", language.code)
            .addFormDataPart("isCreateSearchablePdf", "true")
            .build()
    }

    fun mapToImageParsingResult(response: ImageParsingResponse, id: Long): ImageParsingResult {
        return with(response) {
            ImageParsingResult(
                id = id,
                parsedResults = parsedResults.map(::mapToParsedImage),
                ocrExitCode = ocrExitCode,
                isErroredOnProcessing = isErroredOnProcessing,
                searchablePDFURL = searchablePDFURL
            )
        }
    }

    private fun mapToParsedImage(response: ParsedImageResponse): ParsedImage {
        return with(response) {
            ParsedImage(
                fileParseExitCode = fileParseExitCode,
                parsedText = parsedText,
                errorMessage = errorMessage,
                errorDetails = errorDetails
            )
        }
    }
}