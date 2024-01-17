package com.arshapshap.versati.feature.charts.impl.data.mapper

import com.arshapshap.versati.core.database.model.chartsfeature.ChartLocal
import com.arshapshap.versati.feature.charts.api.model.ChartInfo
import com.arshapshap.versati.feature.charts.api.model.ChartType
import com.arshapshap.versati.feature.charts.api.model.Dataset
import com.arshapshap.versati.feature.charts.impl.BuildConfig
import com.arshapshap.versati.feature.charts.impl.data.model.ChartInfoSerializable
import com.arshapshap.versati.feature.charts.impl.data.model.DataSerializable
import com.arshapshap.versati.feature.charts.impl.data.model.DatasetSerializable
import com.arshapshap.versati.feature.charts.impl.data.utils.fromJson
import com.arshapshap.versati.feature.charts.impl.data.utils.toBase64
import com.arshapshap.versati.feature.charts.impl.data.utils.toJson
import okhttp3.internal.toHexString

internal class ChartsMapper {

    fun createImageUrl(chartInfo: ChartInfo): String = with(chartInfo) {
        val url = StringBuilder(BuildConfig.QUICKCHART_BASE_URL)
        url.append("chart")
        url.append("?c=${chartInfo.toSerializable().toJson().toBase64()}")
        url.append("&encoding=base64")
        url.append("&w=${chartInfo.width}")
        url.append("&h=${chartInfo.height}")
        url.append("&bkg=#${chartInfo.backgroundColor}")
        return url.toString()
    }

    fun mapToLocal(chartInfo: ChartInfo, id: Long, imageUrl: String): ChartLocal {
        return ChartLocal(
            id = id,
            type = chartInfo.type.name,
            xAxisLabelsJson = chartInfo.xAxisLabels.toJson(),
            datasetsJson = chartInfo.datasets.toSerializable().toJson(),
            width = chartInfo.width,
            height = chartInfo.height,
            backgroundColor = chartInfo.backgroundColor,
            imageUrl = imageUrl
        )
    }

    fun mapFromLocal(local: ChartLocal): ChartInfo {
        return ChartInfo(
            id = local.id,
            type = ChartType.valueOf(local.type),
            xAxisLabels = local.xAxisLabelsJson.fromJson(),
            datasets = local.datasetsJson.fromJson<List<DatasetSerializable>>().fromSerializable(),
            width = local.width,
            height = local.height,
            backgroundColor = local.backgroundColor,
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
        return this.map {
            DatasetSerializable(
                label = it.label,
                data = it.data,
                borderColor = "#${it.borderColor.toHexString()}",
                borderWidth = it.borderWidth,
                fill = it.fill,
                backgroundColor = "#${it.backgroundColor.toHexString()}"
            )
        }
    }

    private fun List<DatasetSerializable>.fromSerializable(): List<Dataset> {
        return this.map {
            Dataset(
                label = it.label,
                data = it.data,
                borderColor = it.borderColor.drop(1).toInt(16), // drop(1) for dropping "#" symbol
                borderWidth = it.borderWidth,
                fill = it.fill,
                backgroundColor = it.backgroundColor.drop(1).toInt(16)
            )
        }
    }
}