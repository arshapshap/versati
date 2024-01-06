package com.arshapshap.versati.core.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

private const val baseRoute = "imageparsing_feature"

sealed interface ImageParsingFeature {

    data object Parsing : ImageParsingFeature {

        private const val screen = "parsing"
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
}