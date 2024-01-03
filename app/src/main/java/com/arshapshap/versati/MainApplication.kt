package com.arshapshap.versati

import android.app.Application
import com.arshapshap.versati.core.network.di.networkModule
import com.arshapshap.versati.feature.imageparsing.di.imageParsingFeatureModule
import com.arshapshap.versati.feature.qrcodes.di.qrCodesFeatureModule
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                qrCodesFeatureModule,
                imageParsingFeatureModule,
                networkModule
            )
        }
    }
}