package com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodeshistory.contract

import com.arshapshap.versati.feature.qrcodes.api.domain.model.QRCodeInfo

internal data class QRCodesHistoryState(
    val history: List<QRCodeInfo> = listOf(),
    val showDialogToConfirmClear: Boolean = false
)