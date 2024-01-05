package com.arshapshap.versati.feature.qrcodes.impl.presentation.requesthistory.contract

import com.arshapshap.versati.feature.qrcodes.api.domain.model.QRCodeInfo

internal data class RequestHistoryState(
    val history: List<QRCodeInfo> = listOf(),
    val showDialogToConfirmClear: Boolean = false
)