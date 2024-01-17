package com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.contract

internal data class ChartGenerationState(
    val chartImageUrl: String = "",
    val success: Boolean = false,
    val optionsChanged: Boolean = true
)