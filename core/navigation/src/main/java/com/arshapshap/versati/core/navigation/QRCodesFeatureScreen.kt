package com.arshapshap.versati.core.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class QRCodesFeatureScreen : ScreenProvider {

    data class QRCodeGeneration(val id: Long) : QRCodesFeatureScreen()

    data object RequestsHistory : QRCodesFeatureScreen()
}