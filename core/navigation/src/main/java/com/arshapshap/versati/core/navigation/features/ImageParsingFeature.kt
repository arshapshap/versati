package com.arshapshap.versati.core.navigation.features

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.arshapshap.versati.core.navigation.base.BaseFeature

sealed class ImageParsingFeature(screenName: String) : BaseFeature(featureRoute, screenName) {

    companion object : BaseFeature.Companion("imageparsing_feature")

    data object Parsing : ImageParsingFeature("parsing") {

        const val idArgument = "id"
        val arguments = listOf(
            navArgument(idArgument) {
                type = NavType.LongType
                defaultValue = 0L
            }
        )

        fun destination(id: Long = 0L) = destination(mapOf(arguments[0] to id))
        override val route get() = route(arguments)
    }

    data object ParsingHistory : ImageParsingFeature("history")
}