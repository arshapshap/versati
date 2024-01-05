package com.arshapshap.versati.feature.qrcodes.impl.presentation.requesthistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arshapshap.versati.feature.qrcodes.api.domain.usecase.ClearHistoryUseCase
import com.arshapshap.versati.feature.qrcodes.api.domain.usecase.GetRequestHistoryUseCase
import com.arshapshap.versati.feature.qrcodes.impl.presentation.requesthistory.contract.RequestHistorySideEffect
import com.arshapshap.versati.feature.qrcodes.impl.presentation.requesthistory.contract.RequestHistoryState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

internal class RequestHistoryViewModel(
    private val getRequestHistoryUseCase: GetRequestHistoryUseCase,
    private val clearHistoryUseCase: ClearHistoryUseCase
) : ContainerHost<RequestHistoryState, RequestHistorySideEffect>, ViewModel() {

    override val container =
        viewModelScope
            .container<RequestHistoryState, RequestHistorySideEffect>(RequestHistoryState())

    init {
        loadHistory()
    }

    fun openQRCode(id: Long) = intent {
        postSideEffect(RequestHistorySideEffect.OpenQRCode(id))
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
        val list = getRequestHistoryUseCase().asReversed()
        reduce { state.copy(history = list) }
    }
}