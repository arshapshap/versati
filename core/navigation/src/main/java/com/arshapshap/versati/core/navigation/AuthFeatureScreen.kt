package com.arshapshap.versati.core.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class AuthFeatureScreen : ScreenProvider {
    data object SignIn : AuthFeatureScreen()

    data object Register : AuthFeatureScreen()
}