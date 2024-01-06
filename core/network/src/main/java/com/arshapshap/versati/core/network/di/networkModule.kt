package com.arshapshap.versati.core.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


val networkModule = module {
    factory<OkHttpClient> {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    single<Retrofit> { (baseUrl: String) ->
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
        }
        Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(get<OkHttpClient>())
            .baseUrl(baseUrl)
            .build()
    }
}