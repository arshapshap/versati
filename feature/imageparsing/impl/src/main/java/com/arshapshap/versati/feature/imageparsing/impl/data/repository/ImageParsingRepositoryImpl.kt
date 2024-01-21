package com.arshapshap.versati.feature.imageparsing.impl.data.repository

import android.graphics.Bitmap
import com.arshapshap.versati.core.database.dao.imageparsingfeature.ParsingResultDao
import com.arshapshap.versati.core.firebase.RemoteConfig
import com.arshapshap.versati.feature.imageparsing.api.domain.model.Language
import com.arshapshap.versati.feature.imageparsing.api.domain.model.ParsingResult
import com.arshapshap.versati.feature.imageparsing.api.domain.repository.ImageParsingRepository
import com.arshapshap.versati.feature.imageparsing.impl.data.mapper.ImageParsingMapper
import com.arshapshap.versati.feature.imageparsing.impl.data.network.OCRApi

private const val MAX_HISTORY_SIZE = 20

internal class ImageParsingRepositoryImpl(
    private val api: OCRApi,
    private val dao: ParsingResultDao,
    private val mapper: ImageParsingMapper
) : ImageParsingRepository {

    override suspend fun parseImageByUrl(url: String, language: Language): ParsingResult {
        val requestBody = mapper.mapToRequestBody(url, language)
        val response = api.parseImageByUrl(RemoteConfig.OCRApiKey, requestBody)
        val parsingResult = mapper.mapFromRemote(remote = response, id = 0, sourceUrl = url)

        if (parsingResult.ocrExitCode == 1) {
            val local = mapper.mapToLocal(parsingResult)
            val id = dao.add(local)
            if (dao.getCount() > MAX_HISTORY_SIZE)
                dao.deleteOldest()
            return parsingResult.copy(id = id)
        }

        return parsingResult
    }

    override suspend fun parseImageBitmap(image: Bitmap, language: Language): ParsingResult {
        TODO("Not yet implemented")
    }

    override suspend fun getParsingHistory(): List<ParsingResult> {
        return dao.getAll().map(mapper::mapFromLocal)
    }

    override suspend fun clearHistory() {
        dao.deleteAll()
    }

    override suspend fun getParsingResultById(id: Long): ParsingResult? {
        return dao.getById(id)?.let { mapper.mapFromLocal(it) }
    }
}