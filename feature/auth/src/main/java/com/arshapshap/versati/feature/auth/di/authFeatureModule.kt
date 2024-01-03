package com.arshapshap.versati.feature.auth.di

import com.arshapshap.versati.feature.auth.data.repository.AuthRepositoryImpl
import com.arshapshap.versati.feature.auth.domain.repository.AuthRepository
import com.arshapshap.versati.feature.auth.domain.usecase.LogInUseCase
import com.arshapshap.versati.feature.auth.domain.usecase.LogOutUseCase
import com.arshapshap.versati.feature.auth.domain.usecase.RegisterUseCase
import org.koin.dsl.module

val authFeatureModule = module {
    // Data
    factory<AuthRepository> { AuthRepositoryImpl() }

    // Domain
    factory<LogInUseCase> { LogInUseCase(get<AuthRepository>()) }
    factory<LogOutUseCase> { LogOutUseCase(get<AuthRepository>()) }
    factory<RegisterUseCase> { RegisterUseCase(get<AuthRepository>()) }
}