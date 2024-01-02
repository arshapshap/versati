package com.arshapshap.versati.feature.qrcodes.data.mapper

import com.arshapshap.versati.feature.qrcodes.BuildConfig
import com.arshapshap.versati.feature.qrcodes.domain.model.QRCodeOptions

internal class QRCodesMapper {

    fun createImageUrl(options: QRCodeOptions): String = with(options) {
        val url = StringBuilder(BuildConfig.GOQR_BASE_URL)
        url.append("create-qr-code/")
        url.append("?data=$data")
        url.append("&size=${size}x${size}")
        url.append("&color=${color.toHexColor()}")
        url.append("&bgcolor=${backgroundColor.toHexColor()}")
        url.append("&qzone=$quietZone")
        url.append("&format=${format.name.lowercase()}")

        return url.toString()
    }

    private fun Long.toHexColor() = this.toString(16).padStart(6, '0')
}