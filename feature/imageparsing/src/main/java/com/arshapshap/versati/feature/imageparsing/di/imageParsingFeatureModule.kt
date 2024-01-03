package com.arshapshap.versati.feature.imageparsing.di

import com.arshapshap.versati.core.database.dao.imageparsingfeature.ParsingResultDao
import com.arshapshap.versati.feature.imageparsing.BuildConfig
import com.arshapshap.versati.feature.imageparsing.data.mapper.ImageParsingMapper
import com.arshapshap.versati.feature.imageparsing.data.network.OCRApi
import com.arshapshap.versati.feature.imageparsing.data.repository.ImageParsingRepositoryImpl
import com.arshapshap.versati.feature.imageparsing.domain.repository.ImageParsingRepository
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit

val imageParsingFeatureModule = module {
    // Data
    factory<OCRApi> { get<Retrofit> { parametersOf(BuildConfig.OCR_BASE_URL) }.create(OCRApi::class.java) }
    factory<ImageParsingMapper> { ImageParsingMapper() }
    factory<ImageParsingRepository> {
        ImageParsingRepositoryImpl(
            get<OCRApi>(),
            get<ParsingResultDao>(),
            get<ImageParsingMapper>(),
        )
    }
}