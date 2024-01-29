package com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodeshistory.contract

import androidx.compose.runtime.Immutable
import com.arshapshap.versati.feature.qrcodes.api.domain.model.QRCodeInfo

@Immutable
internal data class QRCodesHistoryState(
    val history: List<QRCodeInfo> = listOf(),
    val showDialogToConfirmClear: Boolean = false
)