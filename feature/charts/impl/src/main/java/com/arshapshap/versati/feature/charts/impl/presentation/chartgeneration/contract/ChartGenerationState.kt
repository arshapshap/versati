package com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.contract

import android.graphics.Bitmap
import androidx.compose.runtime.Immutable
import com.arshapshap.versati.feature.charts.api.model.ChartType

@Immutable
internal data class ChartGenerationState(
    val labels: String = "",
    val showLabelsInputError: Boolean = false,
    val datasets: List<DatasetState> = listOf(DatasetState()),
    val chartType: ChartType = ChartType.Bar,
    val expandedDataset: Int = 0,
    val chartImageUrl: String = "",
    val loading: Boolean = false,
    val success: Boolean = false,
    val bitmap: Bitmap? = null,
    val optionsChanged: Boolean = true,
    val loadingNumber: Int = 0
)

internal data class DatasetState(
    val label: String = "",
    val showLabelInputError: Boolean = false,
    val data: String = "",
    val showDataInputError: Boolean = false
)