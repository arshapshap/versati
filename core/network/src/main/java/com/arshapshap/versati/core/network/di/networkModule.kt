package com.arshapshap.versati.core.network.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val networkModule = module {
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single<Retrofit> { (baseUrl: String) ->
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .build()
    }
}