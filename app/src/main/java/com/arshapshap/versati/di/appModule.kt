package com.arshapshap.versati.di

import android.content.SharedPreferences
import com.arshapshap.versati.data.LastOpenedFeatureRepository
import org.koin.dsl.module

internal val appModule = module {
    // Data
    factory<LastOpenedFeatureRepository> { LastOpenedFeatureRepository(get<SharedPreferences>()) }
}