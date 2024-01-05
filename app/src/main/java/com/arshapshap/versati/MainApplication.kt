package com.arshapshap.versati

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.arshapshap.versati.core.database.di.databaseModule
import com.arshapshap.versati.core.network.di.networkModule
import com.arshapshap.versati.feature.auth.impl.di.authFeatureModule
import com.arshapshap.versati.feature.auth.impl.navigation.authFeatureScreenModule
import com.arshapshap.versati.feature.imageparsing.impl.di.imageParsingFeatureModule
import com.arshapshap.versati.feature.qrcodes.impl.di.qrCodesFeatureModule
import com.arshapshap.versati.feature.qrcodes.impl.navigation.qrCodesFeatureScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(
                databaseModule,
                networkModule,
                authFeatureModule,
                imageParsingFeatureModule,
                qrCodesFeatureModule,
            )
        }

        ScreenRegistry {
            authFeatureScreenModule()
            qrCodesFeatureScreenModule()
        }
    }
}