package com.arshapshap.feature.charts.impl.di

import com.arshapshap.feature.charts.api.repository.ChartsRepository
import com.arshapshap.feature.charts.api.usecase.ClearHistoryUseCase
import com.arshapshap.feature.charts.api.usecase.CreateChartUseCase
import com.arshapshap.feature.charts.api.usecase.GetChartInfoByIdUseCase
import com.arshapshap.feature.charts.api.usecase.GetChartsHistoryUseCase
import com.arshapshap.feature.charts.impl.data.mapper.ChartsMapper
import com.arshapshap.feature.charts.impl.data.repository.ChartsRepositoryImpl
import com.arshapshap.versati.core.database.dao.chartsfeature.ChartDao
import org.koin.dsl.module

val chartsFeatureModule = module {
    // Data
    factory<ChartsMapper> { ChartsMapper() }
    factory<ChartsRepository> {
        ChartsRepositoryImpl(
            get<ChartDao>(),
            get<ChartsMapper>()
        )
    }

    // Domain
    factory<ClearHistoryUseCase> { ClearHistoryUseCase(get<ChartsRepository>()) }
    factory<CreateChartUseCase> { CreateChartUseCase(get<ChartsRepository>()) }
    factory<GetChartInfoByIdUseCase> { GetChartInfoByIdUseCase(get<ChartsRepository>()) }
    factory<GetChartsHistoryUseCase> { GetChartsHistoryUseCase(get<ChartsRepository>()) }

    // Presentation
//    factory<QRCodeGenerationViewModel> { (id: Long) ->
//        QRCodeGenerationViewModel(id, get<CreateQRCodeUseCase>(), get<GetQRCodeInfoByIdUseCase>())
//    }
//    factory<QRCodesHistoryViewModel> {
//        QRCodesHistoryViewModel(get<GetQRCodesHistoryUseCase>(), get<ClearHistoryUseCase>())
//    }
}