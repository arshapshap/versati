package com.arshapshap.versati.feature.imageparsing.data.network

import com.arshapshap.versati.feature.imageparsing.data.network.response.ImageParsingRemote
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

internal interface OCRApi {

    @POST("image")
    suspend fun parseImageByUrl(@Body body: RequestBody): ImageParsingRemote

    @Multipart
    @POST("image")
    suspend fun parseImage(@Part("file") file: MultipartBody.Part, @Body body: RequestBody): ImageParsingRemote
}