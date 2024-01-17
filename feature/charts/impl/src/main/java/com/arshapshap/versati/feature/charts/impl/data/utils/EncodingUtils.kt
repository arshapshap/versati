package com.arshapshap.versati.feature.charts.impl.data.utils

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.util.Base64

inline fun <reified T> T.toJson(): String = Json.encodeToString(this)

inline fun <reified T> String.fromJson(): T = Json.decodeFromString<T>(this)

fun String.toBase64(): String = Base64.getEncoder().encodeToString(this.toByteArray())

fun String.encodeUrl(): String = URLEncoder.encode(this, "utf-8")