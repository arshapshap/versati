package com.arshapshap.versati.feature.settings.impl.di

import com.arshapshap.versati.feature.auth.api.domain.usecase.GetCurrentUserUseCase
import com.arshapshap.versati.feature.auth.api.domain.usecase.LogOutUseCase
import com.arshapshap.versati.feature.settings.impl.presentation.settings.SettingsViewModel
import org.koin.dsl.module

val settingsFeatureModule = module {
    factory<SettingsViewModel> {
        SettingsViewModel(
            get<GetCurrentUserUseCase>(),
            get<LogOutUseCase>()
        )
    }
}