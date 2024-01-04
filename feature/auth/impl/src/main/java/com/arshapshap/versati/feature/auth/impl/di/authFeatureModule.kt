package com.arshapshap.versati.feature.auth.impl.di

import com.arshapshap.versati.feature.auth.api.domain.repository.AuthRepository
import com.arshapshap.versati.feature.auth.api.domain.usecase.LogOutUseCase
import com.arshapshap.versati.feature.auth.api.domain.usecase.RegisterUseCase
import com.arshapshap.versati.feature.auth.api.domain.usecase.SignInUseCase
import com.arshapshap.versati.feature.auth.impl.data.mapper.AuthMapper
import com.arshapshap.versati.feature.auth.impl.data.repository.AuthRepositoryImpl
import com.arshapshap.versati.feature.auth.impl.presentation.register.RegisterScreenModel
import com.arshapshap.versati.feature.auth.impl.presentation.signin.SignInScreenModel
import org.koin.dsl.module

val authFeatureModule = module {
    // Data
    factory<AuthMapper> { AuthMapper() }
    factory<AuthRepository> { AuthRepositoryImpl(get<AuthMapper>()) }

    // Domain
    factory<SignInUseCase> { SignInUseCase(get<AuthRepository>()) }
    factory<LogOutUseCase> { LogOutUseCase(get<AuthRepository>()) }
    factory<RegisterUseCase> { RegisterUseCase(get<AuthRepository>()) }

    // Presentation
    factory<SignInScreenModel> { SignInScreenModel(get<SignInUseCase>()) }
    factory<RegisterScreenModel> { RegisterScreenModel(get<RegisterUseCase>()) }
}