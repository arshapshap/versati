package com.arshapshap.versati.feature.charts.impl.presentation.chartshistory.contract

internal sealed interface ChartsHistorySideEffect {
    data class OpenChart(
        val id: Long
    ) : ChartsHistorySideEffect
}