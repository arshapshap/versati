package com.arshapshap.versati.core.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

private const val baseRoute = "qrcodes_feature"

sealed interface QRCodesFeature {

    data object QRCodeGeneration : QRCodesFeature {

        private const val screen = "qrcode_generation"
        const val idArgument = "id"
        const val route = "$baseRoute/$screen?$idArgument={$idArgument}"

        val arguments = listOf(
            navArgument(idArgument) {
                type = NavType.LongType
                defaultValue = 0L
            }
        )

        fun destination(id: Long = 0L) = "$baseRoute/$screen?$idArgument=$id"
    }

    data object RequestHistory : QRCodesFeature {

        const val route = "$baseRoute/history"

        fun destination() = route
    }
}