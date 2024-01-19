package com.arshapshap.versati.data

import android.content.SharedPreferences

private const val LAST_OPENED_FEATURE_ROUTE = "LAST_OPENED_FEATURE"

internal class LastOpenedFeatureRepository(
    private val sharedPreferences: SharedPreferences
) {

    fun getLastOpenedFeature(): String? {
        return sharedPreferences.getString(LAST_OPENED_FEATURE_ROUTE, null)
    }

    fun setLastOpenedFeature(string: String) {
        sharedPreferences.edit().putString(LAST_OPENED_FEATURE_ROUTE, string).apply()
    }
}