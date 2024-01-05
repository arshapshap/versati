package com.arshapshap.versati.core.navigation

sealed class AuthFeatureScreen {

    data object SignIn : AuthFeatureScreen()

    data object Register : AuthFeatureScreen()
}