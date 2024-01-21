package com.arshapshap.versati.core.navigation.features

sealed interface SettingsFeature {

    companion object {

        const val featureRoute = "settings_feature"
    }


    data object Settings : SettingsFeature {

        const val route = "$featureRoute/settings"

        fun destination() = route
    }
}