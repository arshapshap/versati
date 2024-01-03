package com.arshapshap.versati.feature.imageparsing.data.repository

import android.graphics.Bitmap
import com.arshapshap.versati.feature.imageparsing.data.database.ImageParsingDao
import com.arshapshap.versati.feature.imageparsing.data.mapper.ImageParsingMapper
import com.arshapshap.versati.feature.imageparsing.data.network.OCRApi
import com.arshapshap.versati.feature.imageparsing.domain.model.ImageParsingResult
import com.arshapshap.versati.feature.imageparsing.domain.model.Language
import com.arshapshap.versati.feature.imageparsing.domain.repository.ImageParsingRepository

internal class ImageParsingRepositoryImpl(
    private val api: OCRApi,
    private val local: ImageParsingDao,
    private val mapper: ImageParsingMapper
) : ImageParsingRepository {

    override suspend fun parseImageByUrl(url: String, language: Language): ImageParsingResult {
        val requestBody = mapper.mapToRequestBody(url, language)
        val response = api.parseImageByUrl(requestBody)

        val mapped = mapper.mapToImageParsingResult(response, 0)
        val id = local.saveParsingResult(mapped)
        return mapped.copy(id = id)
    }

    override suspend fun parseImageBitmap(image: Bitmap, language: Language): ImageParsingResult {
        TODO("Not yet implemented")
    }

    override suspend fun getParsingHistory(): List<ImageParsingResult> {
        return local.getParsingHistory()
    }

    override suspend fun getParsingInfoById(id: Long): ImageParsingResult {
        TODO("Not yet implemented")
    }
}