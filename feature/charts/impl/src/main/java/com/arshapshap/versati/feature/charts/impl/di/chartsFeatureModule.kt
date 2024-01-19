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
import com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.mapper.ChartGenerationStateMapper
import com.arshapshap.versati.feature.charts.impl.presentation.chartshistory.ChartsHistoryViewModel
import org.koin.dsl.module

val chartsFeatureModule = module {
    // Data
    factory<ChartsMapper> { ChartsMapper() }
    factory<ChartsRepository> {
        ChartsRepositoryImpl(
            dao = get<ChartDao>(),
            mapper = get<ChartsMapper>()
        )
    }

    // Domain
    factory<ClearHistoryUseCase> { ClearHistoryUseCase(repository = get<ChartsRepository>()) }
    factory<CreateChartUseCase> { CreateChartUseCase(repository = get<ChartsRepository>()) }
    factory<GetChartInfoByIdUseCase> { GetChartInfoByIdUseCase(repository = get<ChartsRepository>()) }
    factory<GetChartsHistoryUseCase> { GetChartsHistoryUseCase(repository = get<ChartsRepository>()) }

    // Presentation
    factory<ChartGenerationStateMapper> { ChartGenerationStateMapper() }
    factory<ChartGenerationViewModel> { (id: Long) ->
        ChartGenerationViewModel(
            chartInfoId = id,
            createChartUseCase = get<CreateChartUseCase>(),
            getChartInfoByIdUseCase = get<GetChartInfoByIdUseCase>(),
            mapper = get<ChartGenerationStateMapper>()
        )
    }
    factory<ChartsHistoryViewModel> {
        ChartsHistoryViewModel(get<GetChartsHistoryUseCase>(), get<ClearHistoryUseCase>())
    }
}