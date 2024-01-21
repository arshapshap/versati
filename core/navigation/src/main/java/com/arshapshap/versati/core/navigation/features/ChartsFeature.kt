package com.arshapshap.versati.core.navigation.features

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface ChartsFeature {

    companion object {

        const val featureRoute = "charts_feature"
    }

    data object ChartGeneration : ChartsFeature {

        private const val screen = "chart_generation"
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

    data object ChartsHistory : ChartsFeature {

        const val route = "$featureRoute/history"

        fun destination() = route
    }
}