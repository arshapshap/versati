package com.arshapshap.versati.core.firebase

import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.remoteConfig

object RemoteConfig {

    val OCRApiKey
        get() = Firebase.remoteConfig.getString("OCR_API_KEY")

}