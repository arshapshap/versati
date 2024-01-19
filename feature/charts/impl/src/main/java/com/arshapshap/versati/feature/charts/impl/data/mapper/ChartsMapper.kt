package com.arshapshap.versati.feature.charts.impl.data.mapper

import android.util.Log
import com.arshapshap.versati.core.database.model.chartsfeature.ChartLocal
import com.arshapshap.versati.feature.charts.api.model.ChartInfo
import com.arshapshap.versati.feature.charts.api.model.ChartType
import com.arshapshap.versati.feature.charts.api.model.Dataset
import com.arshapshap.versati.feature.charts.impl.BuildConfig
import com.arshapshap.versati.feature.charts.impl.data.model.ChartInfoSerializable
import com.arshapshap.versati.feature.charts.impl.data.model.DataSerializable
import com.arshapshap.versati.feature.charts.impl.data.model.DatasetSerializable
import com.arshapshap.versati.feature.charts.impl.data.utils.encodeUrl
import com.arshapshap.versati.feature.charts.impl.data.utils.fromJson
import com.arshapshap.versati.feature.charts.impl.data.utils.toJson
import okhttp3.internal.toHexString

internal class ChartsMapper {

    fun createImageUrl(chartInfo: ChartInfo): String = with(chartInfo) {
        val url = StringBuilder(BuildConfig.QUICKCHART_BASE_URL)
        url.append("chart")
        url.append("?c=${chartInfo.toSerializable().toJson().encodeUrl()}")
        url.append("&encoding=url")
        chartInfo.width?.let { url.append("&w=$it") }
        chartInfo.height?.let { url.append("&h=$it") }
        chartInfo.backgroundColor?.let { url.append("&bkg=#$it") }

        if (BuildConfig.DEBUG) {
            Log.d("Charts Mapper", chartInfo.toSerializable().toJson())
            Log.d("Charts Mapper", url.toString())
        }
        return url.toString()
    }

    fun mapToLocal(chartInfo: ChartInfo, id: Long, imageUrl: String): ChartLocal {
        return ChartLocal(
            id = id,
            type = chartInfo.type.name,
            xAxisLabelsJson = chartInfo.xAxisLabels.toJson(),
            datasetsJson = chartInfo.datasets.toSerializable().toJson(),
            width = chartInfo.width ?: -1,
            height = chartInfo.height ?: -1,
            backgroundColor = chartInfo.backgroundColor ?: -1,
            imageUrl = imageUrl
        )
    }

    fun mapFromLocal(local: ChartLocal): ChartInfo {
        return ChartInfo(
            id = local.id,
            type = ChartType.valueOf(local.type),
            xAxisLabels = local.xAxisLabelsJson.fromJson(),
            datasets = local.datasetsJson.fromJson<List<DatasetSerializable>>().fromSerializable(),
            width = if (local.width >= 0) local.width else null,
            height = if (local.height >= 0) local.height else null,
            backgroundColor = if (local.backgroundColor >= 0) local.backgroundColor else null,
            imageUrl = local.imageUrl
        )
    }

    private fun ChartInfo.toSerializable(): ChartInfoSerializable {
        return ChartInfoSerializable(
            type = type.name.lowercase(),
            data = DataSerializable(
                labels = xAxisLabels,
                datasets = datasets.toSerializable(),
            )
        )
    }

    private fun List<Dataset>.toSerializable(): List<DatasetSerializable> {
        return this.map { dataset ->
            DatasetSerializable(
                label = dataset.label,
                data = dataset.data,
                borderColor = dataset.borderColor?.let { "#${it.toHexString()}" },
                borderWidth = dataset.borderWidth,
                fill = dataset.fill,
                backgroundColor = dataset.backgroundColor?.let { "#${it.toHexString()}" }
            )
        }
    }

    private fun List<DatasetSerializable>.fromSerializable(): List<Dataset> {
        return this.map {
            Dataset(
                label = it.label,
                data = it.data,
                borderColor = it.borderColor?.drop(1)?.toInt(16), // drop(1) for dropping "#" symbol
                borderWidth = it.borderWidth,
                fill = it.fill,
                backgroundColor = it.backgroundColor?.drop(1)?.toInt(16)
            )
        }
    }
}