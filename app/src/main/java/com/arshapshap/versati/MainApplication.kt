package com.arshapshap.versati

import android.app.Application
import com.arshapshap.versati.core.database.di.databaseModule
import com.arshapshap.versati.core.network.di.networkModule
import com.arshapshap.versati.core.storage.storageModule
import com.arshapshap.versati.di.appModule
import com.arshapshap.versati.feature.auth.impl.di.authFeatureModule
import com.arshapshap.versati.feature.charts.impl.di.chartsFeatureModule
import com.arshapshap.versati.feature.imageparsing.impl.di.imageParsingFeatureModule
import com.arshapshap.versati.feature.qrcodes.impl.di.qrCodesFeatureModule
import com.arshapshap.versati.feature.settings.impl.di.settingsFeatureModule
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

        startKoin {
            androidContext(this@MainApplication)
            modules(
                appModule,
                databaseModule,
                networkModule,
                storageModule,
                authFeatureModule,
                chartsFeatureModule,
                imageParsingFeatureModule,
                qrCodesFeatureModule,
                settingsFeatureModule,
            )
        }
    }
}