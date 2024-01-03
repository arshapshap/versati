package com.arshapshap.versati.feature.imageparsing.data.repository

import android.graphics.Bitmap
import com.arshapshap.versati.core.database.dao.imageparsingfeature.ParsingResultDao
import com.arshapshap.versati.feature.imageparsing.data.mapper.ImageParsingMapper
import com.arshapshap.versati.feature.imageparsing.data.network.OCRApi
import com.arshapshap.versati.feature.imageparsing.domain.model.ParsingResult
import com.arshapshap.versati.feature.imageparsing.domain.model.Language
import com.arshapshap.versati.feature.imageparsing.domain.repository.ImageParsingRepository

private const val MAX_HISTORY_SIZE = 20
internal class ImageParsingRepositoryImpl(
    private val api: OCRApi,
    private val dao: ParsingResultDao,
    private val mapper: ImageParsingMapper
) : ImageParsingRepository {

    override suspend fun parseImageByUrl(url: String, language: Language): ParsingResult {
        val requestBody = mapper.mapToRequestBody(url, language)
        val response = api.parseImageByUrl(requestBody)
        val parsingResult = mapper.mapFromRemote(response, 0)

        val id = dao.add(mapper.mapToLocal(parsingResult))
        if (dao.getCount() > MAX_HISTORY_SIZE)
            dao.deleteOldest()

        return parsingResult.copy(id = id)
    }

    override suspend fun parseImageBitmap(image: Bitmap, language: Language): ParsingResult {
        TODO("Not yet implemented")
    }

    override suspend fun getParsingHistory(): List<ParsingResult> {
        return dao.getAll().map(mapper::mapFromLocal)
    }

    override suspend fun getParsingInfoById(id: Long): ParsingResult {
        TODO("Not yet implemented")
    }
}