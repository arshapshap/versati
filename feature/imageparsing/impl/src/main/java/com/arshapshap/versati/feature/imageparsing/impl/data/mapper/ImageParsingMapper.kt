package com.arshapshap.versati.feature.imageparsing.impl.data.mapper

import com.arshapshap.versati.core.database.model.imageparsingfeature.ParsingResultLocal
import com.arshapshap.versati.feature.imageparsing.api.domain.model.Language
import com.arshapshap.versati.feature.imageparsing.api.domain.model.ParsedImage
import com.arshapshap.versati.feature.imageparsing.api.domain.model.ParsingResult
import com.arshapshap.versati.feature.imageparsing.impl.data.network.response.ImageParsingRemote
import com.arshapshap.versati.feature.imageparsing.impl.data.network.response.ParsedImageRemote
import okhttp3.MultipartBody
import okhttp3.RequestBody

private const val PARSED_TEXT_SEPARATOR = "&%42;"

internal class ImageParsingMapper {

    fun mapToRequestBody(url: String, language: Language): RequestBody {
        return MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("url", url)
            .addFormDataPart("language", language.code)
            .addFormDataPart("isCreateSearchablePdf", "true")
            .build()
    }

    fun mapFromRemote(remote: ImageParsingRemote, id: Long): ParsingResult {
        return ParsingResult(
            id = id,
            parsedResults = remote.parsedResults.map(::mapToParsedImage),
            ocrExitCode = remote.ocrExitCode,
            isErroredOnProcessing = remote.isErroredOnProcessing,
            searchablePDFURL = remote.searchablePDFURL
        )
    }

    fun mapToLocal(parsingResult: ParsingResult): ParsingResultLocal {
        return ParsingResultLocal(
            id = parsingResult.id,
            parsedText = parsingResult.parsedResults
                .filter { it.fileParseExitCode == 1 }
                .map { it.copy(parsedText = it.parsedText.replace(PARSED_TEXT_SEPARATOR, "")) }
                .joinToString(PARSED_TEXT_SEPARATOR),
            searchablePDFURL = parsingResult.searchablePDFURL
        )
    }

    fun mapFromLocal(local: ParsingResultLocal): ParsingResult {
        return ParsingResult(
            id = local.id,
            parsedResults = local.parsedText.split(PARSED_TEXT_SEPARATOR)
                .map { ParsedImage(parsedText = it) },
            searchablePDFURL = local.searchablePDFURL
        )
    }

    private fun mapToParsedImage(response: ParsedImageRemote): ParsedImage {
        return ParsedImage(
            fileParseExitCode = response.fileParseExitCode,
            parsedText = response.parsedText,
            errorMessage = response.errorMessage,
            errorDetails = response.errorDetails
        )
    }
}