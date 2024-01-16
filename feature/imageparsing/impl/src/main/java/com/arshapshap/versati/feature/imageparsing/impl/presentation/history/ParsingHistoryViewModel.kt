package com.arshapshap.versati.feature.imageparsing.impl.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arshapshap.versati.feature.imageparsing.api.domain.usecase.ClearHistoryUseCase
import com.arshapshap.versati.feature.imageparsing.api.domain.usecase.GetParsingHistoryUseCase
import com.arshapshap.versati.feature.imageparsing.impl.presentation.history.contract.ParsingHistorySideEffect
import com.arshapshap.versati.feature.imageparsing.impl.presentation.history.contract.ParsingHistoryState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

internal class ParsingHistoryViewModel(
    private val getParsingHistoryUseCase: GetParsingHistoryUseCase,
    private val clearHistoryUseCase: ClearHistoryUseCase
) : ContainerHost<ParsingHistoryState, ParsingHistorySideEffect>, ViewModel() {

    override val container =
        viewModelScope
            .container<ParsingHistoryState, ParsingHistorySideEffect>(ParsingHistoryState())

    init {
        loadHistory()
    }

    fun openParsingResult(id: Long) = intent {
        postSideEffect(ParsingHistorySideEffect.OpenParsingResult(id))
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
        val list = getParsingHistoryUseCase().asReversed()
        reduce { state.copy(history = list) }
    }
}