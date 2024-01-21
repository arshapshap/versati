package com.arshapshap.versati.feature.auth.impl.di

import com.arshapshap.versati.feature.auth.api.domain.repository.AuthRepository
import com.arshapshap.versati.feature.auth.api.domain.usecase.GetCurrentUserUseCase
import com.arshapshap.versati.feature.auth.api.domain.usecase.LogOutUseCase
import com.arshapshap.versati.feature.auth.api.domain.usecase.RegisterUseCase
import com.arshapshap.versati.feature.auth.api.domain.usecase.SignInUseCase
import com.arshapshap.versati.feature.auth.impl.data.mapper.AuthMapper
import com.arshapshap.versati.feature.auth.impl.data.repository.AuthRepositoryImpl
import com.arshapshap.versati.feature.auth.impl.presentation.register.RegisterViewModel
import com.arshapshap.versati.feature.auth.impl.presentation.signin.SignInViewModel
import org.koin.dsl.module

val authFeatureModule = module {
    // Data
    factory<AuthMapper> { AuthMapper() }
    factory<AuthRepository> { AuthRepositoryImpl(get<AuthMapper>()) }

    // Domain
    factory<GetCurrentUserUseCase> { GetCurrentUserUseCase(get<AuthRepository>()) }
    factory<LogOutUseCase> { LogOutUseCase(get<AuthRepository>()) }
    factory<RegisterUseCase> { RegisterUseCase(get<AuthRepository>()) }
    factory<SignInUseCase> { SignInUseCase(get<AuthRepository>()) }

    // Presentation
    factory<RegisterViewModel> { RegisterViewModel(get<RegisterUseCase>()) }
    factory<SignInViewModel> { SignInViewModel(get<SignInUseCase>()) }
}