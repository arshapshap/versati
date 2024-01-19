package com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arshapshap.versati.feature.charts.api.model.ChartInfo
import com.arshapshap.versati.feature.charts.api.model.ChartType
import com.arshapshap.versati.feature.charts.api.model.Dataset
import com.arshapshap.versati.feature.charts.api.usecase.CreateChartUseCase
import com.arshapshap.versati.feature.charts.api.usecase.GetChartInfoByIdUseCase
import com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.contract.ChartGenerationSideEffect
import com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.contract.ChartGenerationState
import com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.contract.DatasetState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import kotlin.math.max

private typealias IntentContext = SimpleSyntax<ChartGenerationState, ChartGenerationSideEffect>

internal class ChartGenerationViewModel(
    chartInfoId: Long,
    private val createChartUseCase: CreateChartUseCase,
    private val getChartInfoByIdUseCase: GetChartInfoByIdUseCase
) : ContainerHost<ChartGenerationState, ChartGenerationSideEffect>, ViewModel() {

    override val container =
        viewModelScope
            .container<ChartGenerationState, ChartGenerationSideEffect>(ChartGenerationState())

    init {
        if (chartInfoId != 0L)
            loadChartInfo(chartInfoId)
    }

    fun createChart() = intent {
        if (state.success && !state.optionsChanged) return@intent

        if (!checkIfOptionsValid()) return@intent

        reduce { state.copy(chartImageUrl = "", bitmap = null, success = false) }
        val result = createChartUseCase(getChartInfo())
        reduce {
            state.copy(
                chartImageUrl = result,
                loading = true,
                optionsChanged = false,
                generationNumber = state.generationNumber + 1
            )
        }
    }

    fun shareChart() = intent {
        postSideEffect(
            ChartGenerationSideEffect.ShareChart(state.bitmap)
        )
    }

    fun navigateToChartsHistory() = intent {
        postSideEffect(ChartGenerationSideEffect.NavigateToChartsHistory)
    }

    fun onImageLoadingSuccess(bitmap: Bitmap?) = intent {
        Log.d("ChartGenerationViewModel", bitmap.toString())
        reduce { state.copy(success = true, loading = false, loadingRetries = 0, bitmap = bitmap) }
    }

    fun onImageLoadingError() = intent {
//        if (retryLoading()) return@intent

        if (state.loading)
            postSideEffect(ChartGenerationSideEffect.TimeoutError)
        reduce { state.copy(success = false, loading = false) }
    }

    @OptIn(OrbitExperimental::class)
    fun updateLabels(value: String) = blockingIntent {
        reduce { state.copy(labels = value, showLabelsInputError = false, optionsChanged = true) }
    }

    fun expandDataset(index: Int) = intent {
        reduce { state.copy(expandedDataset = index) }
    }

    fun deleteDataset(index: Int) = intent {
        reduce {
            state.copy(
                datasets = state.datasets.minus(state.datasets[index]),
                expandedDataset = max(index - 1, 0),
                optionsChanged = true
            )
        }
    }

    fun createDataset() = intent {
        reduce {
            state.copy(
                datasets = state.datasets.plus(DatasetState()),
                expandedDataset = state.datasets.size,
                optionsChanged = true
            )
        }
    }

    @OptIn(OrbitExperimental::class)
    fun updateDatasetLabel(datasetIndex: Int, label: String) = blockingIntent {
        reduce {
            state.copy(
                datasets = state.datasets.replace(
                    index = datasetIndex,
                    element = state.datasets[datasetIndex].copy(
                        label = label,
                        showLabelInputError = false
                    )
                ),
                optionsChanged = true
            )
        }
    }

    @OptIn(OrbitExperimental::class)
    fun updateDataset(datasetIndex: Int, data: String) = blockingIntent {
        reduce {
            state.copy(
                datasets = state.datasets.replace(
                    index = datasetIndex,
                    element = state.datasets[datasetIndex].copy(
                        data = data,
                        showDataInputError = false
                    )
                ),
                optionsChanged = true
            )
        }
    }

    private fun loadChartInfo(id: Long) = intent {
        val chartInfo = getChartInfoByIdUseCase(id) ?: return@intent
//        reduce {
//            state.copy(
//                data = qrCodeInfoById.data,
//                size = qrCodeInfoById.size,
//                qrCodeColor = qrCodeInfoById.color,
//                backgroundColor = qrCodeInfoById.backgroundColor,
//                quietZone = qrCodeInfoById.quietZone,
//                format = qrCodeInfoById.format,
//                qrCodeImageUrl = qrCodeInfoById.imageUrl,
//                optionsChanged = false
//            )
//        }
    }

    private suspend fun IntentContext.checkIfOptionsValid(): Boolean {
        var error = state.labels.isBlank()
        reduce { state.copy(showLabelsInputError = state.labels.isBlank()) }

        state.datasets.forEachIndexed { index, dataset ->
            error = error || dataset.label.isBlank() || dataset.data.isBlank()
            reduce {
                state.copy(
                    datasets = state.datasets.replace(
                        index = index,
                        element = dataset.copy(
                            showLabelInputError = dataset.label.isBlank(),
                            showDataInputError = dataset.data.isBlank()
                        )
                    )
                )
            }
        }

        return !error
    }

    private suspend fun IntentContext.retryLoading(): Boolean {
        if (state.loadingRetries >= 3) {
            reduce { state.copy(loadingRetries = 0) }
            return false
        }
        reduce { state.copy(loadingRetries = state.loadingRetries + 1) }
        Thread.sleep(1000)
        createChart()
        return true
    }

    private fun <T> List<T>.replace(index: Int, element: T): List<T> = this
        .toMutableList()
        .apply {
            this[index] = element
        }

    private fun IntentContext.getChartInfo() = ChartInfo(
        id = 0,
        type = ChartType.Bar,
        xAxisLabels = state.labels.split(',').map { it.trim() },
        datasets = state.datasets.map { dataset ->
            Dataset(
                label = dataset.label.trim(),
                data = dataset.data.split(',').map { it.trim().toIntOrNull() ?: 0 },
                borderColor = null,
                borderWidth = null,
                fill = null,
                backgroundColor = null,
            )
        },
        backgroundColor = null,
        width = null,
        height = null,
        imageUrl = ""
    )
}