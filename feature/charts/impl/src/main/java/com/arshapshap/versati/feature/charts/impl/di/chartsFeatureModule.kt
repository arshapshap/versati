package com.arshapshap.versati.feature.charts.impl.di

import com.arshapshap.versati.core.database.dao.chartsfeature.ChartDao
import com.arshapshap.versati.feature.charts.api.repository.ChartsRepository
import com.arshapshap.versati.feature.charts.api.usecase.ClearHistoryUseCase
import com.arshapshap.versati.feature.charts.api.usecase.CreateChartUseCase
import com.arshapshap.versati.feature.charts.api.usecase.GetChartInfoByIdUseCase
import com.arshapshap.versati.feature.charts.api.usecase.GetChartsHistoryUseCase
import com.arshapshap.versati.feature.charts.impl.data.mapper.ChartsMapper
import com.arshapshap.versati.feature.charts.impl.data.repository.ChartsRepositoryImpl
import com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.ChartGenerationViewModel
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
    factory<ChartGenerationViewModel> { (id: Long) ->
        ChartGenerationViewModel(id, get<CreateChartUseCase>(), get<GetChartInfoByIdUseCase>())
    }
//    factory<ChartsHistoryViewModel> {
//        ChartsHistoryViewModel(get<GetChartsHistoryUseCase>(), get<ClearHistoryUseCase>())
//    }
}