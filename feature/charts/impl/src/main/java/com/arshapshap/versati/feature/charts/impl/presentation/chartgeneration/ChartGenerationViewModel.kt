package com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arshapshap.versati.feature.charts.api.model.ChartInfo
import com.arshapshap.versati.feature.charts.api.usecase.CreateChartUseCase
import com.arshapshap.versati.feature.charts.api.usecase.GetChartInfoByIdUseCase
import com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.contract.ChartGenerationSideEffect
import com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.contract.ChartGenerationState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import kotlin.math.min

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
        if (!state.optionsChanged) return@intent

        reduce { state.copy(chartImageUrl = "", success = false) }
        if (!checkIfOptionsValid()) return@intent

        val result = createChartUseCase(getChartInfo())
        reduce { state.copy(chartImageUrl = result, optionsChanged = false) }
    }

    fun shareChart() = intent {
        postSideEffect(
            ChartGenerationSideEffect.ShareChart(state.chartImageUrl)
        )
    }

    fun navigateToChartsHistory() = intent {
        postSideEffect(ChartGenerationSideEffect.NavigateToChartsHistory)
    }

    fun setSuccess() = intent {
        reduce { state.copy(success = true) }
    }

    private fun loadChartInfo(id: Long) = intent {
        val qrCodeInfoById = getChartInfoByIdUseCase(id) ?: return@intent
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
        TODO()
    }

    private fun IntentContext.getChartInfo(): ChartInfo = TODO()

    private fun validateSize(size: Int?): Int {
        if (size == null || size < 10)
            return 10
        return min(size, 1000)
    }

    private fun validateQuietZone(quietZone: Int?): Int {
        if (quietZone == null)
            return 0
        return min(quietZone, 100)
    }
}