package com.arshapshap.versati.feature.qrcodes.impl.navigation

import cafe.adriel.voyager.core.registry.screenModule
import com.arshapshap.versati.core.navigation.QRCodesFeatureScreen
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.QRCodeGenerationScreen

val qrCodesFeatureScreenModule = screenModule {
    register<QRCodesFeatureScreen.QRCodeGeneration> { QRCodeGenerationScreen(id = it.id) }
}