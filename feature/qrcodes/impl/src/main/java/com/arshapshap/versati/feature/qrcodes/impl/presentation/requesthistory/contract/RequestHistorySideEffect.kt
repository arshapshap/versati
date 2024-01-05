package com.arshapshap.versati.feature.qrcodes.impl.presentation.requesthistory.contract

internal sealed interface RequestHistorySideEffect {
    data class OpenQRCode(
        val id: Long
    ) : RequestHistorySideEffect
}