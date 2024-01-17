package com.arshapshap.feature.charts.api.usecase

import com.arshapshap.feature.charts.api.model.ChartInfo
import com.arshapshap.feature.charts.api.repository.ChartsRepository

class GetChartsHistoryUseCase(
    private val repository: ChartsRepository
) {

    suspend operator fun invoke(): List<ChartInfo> {
        return repository.getChartsHistory()
    }
}