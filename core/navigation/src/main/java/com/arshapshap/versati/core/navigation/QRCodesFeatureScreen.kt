package com.arshapshap.versati.core.navigation

sealed class QRCodesFeatureScreen {

    data class QRCodeGeneration(val id: Long) : QRCodesFeatureScreen()

    data object RequestsHistory : QRCodesFeatureScreen()
}