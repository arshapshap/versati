package com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.contract

internal sealed interface ChartGenerationSideEffect {
    data class ShareChart(
        val imageUrl: String
    ) : ChartGenerationSideEffect

    data object NavigateToChartsHistory : ChartGenerationSideEffect
}