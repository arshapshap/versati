package com.arshapshap.versati.feature.qrcodes.impl.presentation.requesthistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arshapshap.versati.feature.qrcodes.api.domain.usecase.GetRequestHistoryUseCase
import com.arshapshap.versati.feature.qrcodes.impl.presentation.requesthistory.contract.RequestHistorySideEffect
import com.arshapshap.versati.feature.qrcodes.impl.presentation.requesthistory.contract.RequestHistoryState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

internal class RequestHistoryViewModel(
    private val getRequestHistoryUseCase: GetRequestHistoryUseCase
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

    private fun loadHistory() = intent {
        val list = getRequestHistoryUseCase().asReversed()
        reduce { state.copy(history = list) }
    }
}