package com.arshapshap.versati.feature.auth.impl.navigation

import cafe.adriel.voyager.core.registry.screenModule
import com.arshapshap.versati.core.navigation.AuthFeatureScreen
import com.arshapshap.versati.feature.auth.impl.presentation.register.RegisterScreen
import com.arshapshap.versati.feature.auth.impl.presentation.signin.SignInScreen

val authFeatureScreenModule = screenModule {
    register<AuthFeatureScreen.SignIn> { SignInScreen() }
    register<AuthFeatureScreen.Register> { RegisterScreen() }
}