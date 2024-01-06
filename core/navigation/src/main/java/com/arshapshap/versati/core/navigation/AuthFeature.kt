package com.arshapshap.versati.core.navigation

sealed interface AuthFeature {

    companion object {

        const val featureRoute = "auth_feature"
    }


    data object Account : AuthFeature {

        const val route = "$featureRoute/account"

        fun destination() = route
    }

    data object Register : AuthFeature {

        const val route = "$featureRoute/register"

        fun destination() = route
    }

    data object SignIn : AuthFeature {

        const val route = "$featureRoute/sign_in"

        fun destination() = route
    }
}