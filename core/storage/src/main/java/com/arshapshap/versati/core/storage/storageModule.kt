package com.arshapshap.versati.core.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import org.koin.dsl.module

val storageModule = module {
    factory<SharedPreferences> { PreferenceManager.getDefaultSharedPreferences(get<Context>()) }
}