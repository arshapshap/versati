package com.arshapshap.versati.feature.charts.impl.presentation.chartshistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arshapshap.versati.feature.charts.api.usecase.ClearHistoryUseCase
import com.arshapshap.versati.feature.charts.api.usecase.GetChartsHistoryUseCase
import com.arshapshap.versati.feature.charts.impl.presentation.chartshistory.contract.ChartsHistorySideEffect
import com.arshapshap.versati.feature.charts.impl.presentation.chartshistory.contract.ChartsHistoryState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

internal class ChartsHistoryViewModel(
    private val getChartsHistoryUseCase: GetChartsHistoryUseCase,
    private val clearHistoryUseCase: ClearHistoryUseCase
) : ContainerHost<ChartsHistoryState, ChartsHistorySideEffect>, ViewModel() {

    override val container =
        viewModelScope
            .container<ChartsHistoryState, ChartsHistorySideEffect>(ChartsHistoryState())

    init {
        loadHistory()
    }

    fun openChart(id: Long) = intent {
        postSideEffect(ChartsHistorySideEffect.OpenChart(id))
    }

    fun clearHistoryUnconfirmed() = intent {
        reduce { state.copy(showDialogToConfirmClear = true) }
    }

    fun cancelClear() = intent {
        reduce { state.copy(showDialogToConfirmClear = false) }
    }

    fun clearHistoryConfirmed() = intent {
        clearHistoryUseCase()
        reduce { state.copy(history = listOf(), showDialogToConfirmClear = false) }
    }

    private fun loadHistory() = intent {
        val list = getChartsHistoryUseCase().asReversed()
        reduce { state.copy(history = list) }
    }
}