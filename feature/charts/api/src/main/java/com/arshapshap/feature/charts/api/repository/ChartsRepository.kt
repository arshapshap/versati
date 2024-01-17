package com.arshapshap.feature.charts.api.repository

import com.arshapshap.feature.charts.api.model.ChartInfo

interface ChartsRepository {

    suspend fun clearHistory()

    suspend fun createChartImageUrl(chartInfo: ChartInfo): String

    suspend fun getChartInfoById(id: Long): ChartInfo?

    suspend fun getChartsHistory(): List<ChartInfo>
}