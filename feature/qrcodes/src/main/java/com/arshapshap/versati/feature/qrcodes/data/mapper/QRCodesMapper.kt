package com.arshapshap.versati.feature.qrcodes.data.mapper

import com.arshapshap.versati.core.database.model.qrcodesfeature.QRCodeRequestLocal
import com.arshapshap.versati.feature.qrcodes.BuildConfig
import com.arshapshap.versati.feature.qrcodes.domain.model.ImageFormat
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

    fun mapToLocal(options: QRCodeOptions, id: Long): QRCodeRequestLocal {
        return QRCodeRequestLocal(
            id = id,
            data = options.data,
            size = options.size,
            color = options.color,
            backgroundColor = options.backgroundColor,
            quietZone = options.quietZone,
            format = options.format.name.lowercase()
        )
    }

    fun mapFromLocal(local: QRCodeRequestLocal): QRCodeOptions {
        return QRCodeOptions(
            id = local.id,
            data = local.data,
            size = local.size,
            color = local.color,
            backgroundColor = local.backgroundColor,
            quietZone = local.quietZone,
            format = ImageFormat.valueOf(local.format.uppercase())
        )
    }

    private fun Long.toHexColor() = this.toString(16).padStart(6, '0')
}