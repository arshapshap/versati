package com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.contract

import android.graphics.Bitmap

internal sealed interface ChartGenerationSideEffect {
    data class ShareChart(
        val bitmap: Bitmap?
    ) : ChartGenerationSideEffect

    data object NavigateToChartsHistory : ChartGenerationSideEffect

    data object TimeoutError : ChartGenerationSideEffect
}