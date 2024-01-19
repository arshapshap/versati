package com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.mapper

import com.arshapshap.versati.feature.charts.api.model.ChartInfo
import com.arshapshap.versati.feature.charts.api.model.Dataset
import com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.contract.ChartGenerationState
import com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.contract.DatasetState

internal class ChartGenerationStateMapper {

    fun toState(chartInfo: ChartInfo) = ChartGenerationState(
        labels = chartInfo.xAxisLabels.joinToString(", "),
        datasets = chartInfo.datasets.map { dataset ->
            DatasetState(
                label = dataset.label,
                data = dataset.data.joinToString(", ")
            )
        },
        chartImageUrl = chartInfo.imageUrl
    )

    fun fromState(state: ChartGenerationState) = ChartInfo(
        id = 0,
        type = state.chartType,
        xAxisLabels = state.labels.split(',').map { it.trim() },
        datasets = state.datasets.map { dataset ->
            Dataset(
                label = dataset.label.trim(),
                data = dataset.data.split(',').map { it.trim().toIntOrNull() ?: 0 },
                borderColor = null,
                borderWidth = null,
                fill = null,
                backgroundColor = null,
            )
        },
        backgroundColor = null,
        width = null,
        height = null,
        imageUrl = ""
    )
}