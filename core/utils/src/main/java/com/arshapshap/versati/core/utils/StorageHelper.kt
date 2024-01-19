package com.arshapshap.versati.core.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date

class StorageHelper {

    companion object {

        fun getImageUriFromBitmap(
            context: Context,
            bitmap: Bitmap,
            fileNamePrefix: String,
            format: String = "png"
        ): Uri {
            val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val fileName = "${fileNamePrefix}_$timeStamp"
            val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile(fileName, ".$format", storageDir)
            val fileOutputStream = FileOutputStream(file)
            bitmap.compress(getCompressFormat(format), 100, fileOutputStream)
            fileOutputStream.flush()
            fileOutputStream.close()

            return FileProvider.getUriForFile(context, context.packageName + ".provider", file)
        }

        private fun getCompressFormat(format: String): CompressFormat {
            return when (format) {
                "png" -> CompressFormat.PNG
                "jpg", "jpeg" -> CompressFormat.JPEG
                else -> CompressFormat.JPEG
            }
        }
    }
}