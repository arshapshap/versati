package com.arshapshap.versati.core.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface ImageParsingFeature {

    companion object {

        const val featureRoute = "imageparsing_feature"
    }

    data object Parsing : ImageParsingFeature {

        private const val screen = "parsing"
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

    data object ParsingHistory : ImageParsingFeature {

        const val route = "${featureRoute}/history"

        fun destination() = route
    }
}