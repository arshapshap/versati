package com.arshapshap.versati.core.navigation.features

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface QRCodesFeature {

    companion object {

        const val featureRoute = "qrcodes_feature"
    }

    data object QRCodeGeneration : QRCodesFeature {

        private const val screen = "qrcode_generation"
        const val idArgument = "id"
        const val route = "$featureRoute/$screen?$idArgument={$idArgument}"

        val arguments = listOf(
            navArgument(idArgument) {
                type = NavType.LongType
                defaultValue = 0L
            }
        )

        fun destination(id: Long = 0L) = "$featureRoute/$screen?$idArgument=$id"
    }

    data object QRCodesHistory : QRCodesFeature {

        const val route = "$featureRoute/history"

        fun destination() = route
    }
}