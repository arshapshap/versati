package com.arshapshap.feature.charts.api.usecase

import com.arshapshap.feature.charts.api.model.ChartInfo
import com.arshapshap.feature.charts.api.repository.ChartsRepository

class GetChartInfoByIdUseCase(
    private val repository: ChartsRepository
) {

    suspend operator fun invoke(id: Long): ChartInfo? {
        return repository.getChartInfoById(id)
    }
}