package com.arshapshap.versati.core.navigation

private const val baseRoute = "auth_feature"

sealed interface AuthFeature {

    data object SignIn : AuthFeature {

        const val route = "$baseRoute/sign_in"

        fun destination() = route
    }

    data object Register : AuthFeature {

        const val route = "$baseRoute/register"

        fun destination() = route
    }
}