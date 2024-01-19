package com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodeshistory.contract

internal sealed interface QRCodesHistorySideEffect {
    data class OpenQRCode(
        val id: Long
    ) : QRCodesHistorySideEffect
}