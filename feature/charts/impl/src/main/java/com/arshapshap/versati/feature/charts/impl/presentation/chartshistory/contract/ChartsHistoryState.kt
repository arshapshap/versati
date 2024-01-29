package com.arshapshap.versati.feature.charts.impl.presentation.chartshistory.contract

import androidx.compose.runtime.Immutable
import com.arshapshap.versati.feature.charts.api.model.ChartInfo

@Immutable
internal data class ChartsHistoryState(
    val history: List<ChartInfo> = listOf(),
    val showDialogToConfirmClear: Boolean = false
)