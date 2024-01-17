package com.arshapshap.feature.charts.impl.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class ChartInfoSerializable(
    val type: String,
    val data: DataSerializable
)

@Serializable
internal data class DataSerializable(
    val labels: List<String>,
    val datasets: List<DatasetSerializable>,
)

@Serializable
internal data class DatasetSerializable(
    val label: String,
    val data: List<Int>,
    val borderColor: String,
    val borderWidth: Int,
    val fill: Boolean,
    val backgroundColor: String
)