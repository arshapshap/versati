package com.arshapshap.versati.feature.charts.api.usecase

import com.arshapshap.versati.feature.charts.api.model.ChartInfo
import com.arshapshap.versati.feature.charts.api.repository.ChartsRepository

class CreateChartUseCase(
    private val repository: ChartsRepository
) {

    suspend operator fun invoke(chartInfo: ChartInfo): String {
        return repository.createChartImageUrl(chartInfo)
    }
}