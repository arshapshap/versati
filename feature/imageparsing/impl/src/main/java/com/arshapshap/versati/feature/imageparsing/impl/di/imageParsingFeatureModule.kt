package com.arshapshap.versati.feature.imageparsing.impl.di

import com.arshapshap.versati.core.database.dao.imageparsingfeature.ParsingResultDao
import com.arshapshap.versati.feature.imageparsing.api.domain.repository.ImageParsingRepository
import com.arshapshap.versati.feature.imageparsing.api.domain.usecase.GetParsingHistoryUseCase
import com.arshapshap.versati.feature.imageparsing.api.domain.usecase.ParseImageBitmapUseCase
import com.arshapshap.versati.feature.imageparsing.api.domain.usecase.ParseImageByUrlUseCase
import com.arshapshap.versati.feature.imageparsing.impl.BuildConfig
import com.arshapshap.versati.feature.imageparsing.impl.data.mapper.ImageParsingMapper
import com.arshapshap.versati.feature.imageparsing.impl.data.network.OCRApi
import com.arshapshap.versati.feature.imageparsing.impl.data.repository.ImageParsingRepositoryImpl
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

    // Domain
    factory<GetParsingHistoryUseCase> { GetParsingHistoryUseCase(get<ImageParsingRepository>()) }
    factory<ParseImageBitmapUseCase> { ParseImageBitmapUseCase(get<ImageParsingRepository>()) }
    factory<ParseImageByUrlUseCase> { ParseImageByUrlUseCase(get<ImageParsingRepository>()) }
}