package com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodeshistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arshapshap.versati.feature.qrcodes.api.domain.usecase.ClearHistoryUseCase
import com.arshapshap.versati.feature.qrcodes.api.domain.usecase.GetQRCodesHistoryUseCase
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodeshistory.contract.QRCodesHistorySideEffect
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodeshistory.contract.QRCodesHistoryState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

internal class QRCodesHistoryViewModel(
    private val getQrCodesHistoryUseCase: GetQRCodesHistoryUseCase,
    private val clearHistoryUseCase: ClearHistoryUseCase
) : ContainerHost<QRCodesHistoryState, QRCodesHistorySideEffect>, ViewModel() {

    override val container =
        viewModelScope
            .container<QRCodesHistoryState, QRCodesHistorySideEffect>(QRCodesHistoryState())

    init {
        loadHistory()
    }

    fun openQRCode(id: Long) = intent {
        postSideEffect(QRCodesHistorySideEffect.OpenQRCode(id))
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
        val list = getQrCodesHistoryUseCase().asReversed()
        reduce { state.copy(history = list) }
    }
}