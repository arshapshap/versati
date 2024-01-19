package com.arshapshap.versati.feature.charts.impl.presentation.chartshistory.contract

import com.arshapshap.versati.feature.charts.api.model.ChartInfo

internal data class ChartsHistoryState(
    val history: List<ChartInfo> = listOf(),
    val showDialogToConfirmClear: Boolean = false
)