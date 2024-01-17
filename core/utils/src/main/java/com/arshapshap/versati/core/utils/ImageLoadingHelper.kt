package com.arshapshap.versati.core.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest

class ImageLoadingHelper {

    companion object {

        suspend fun loadImageAsBitmap(
            context: Context,
            imageUrl: String,
            format: String = "png"
        ): Bitmap {
            if (format == "svg")
                return loadSvgAsBitmap(context, imageUrl)
            return loadImageAsBitmap(context, imageUrl)
        }

        private suspend fun loadImageAsBitmap(context: Context, imageUrl: String): Bitmap {
            val loader = ImageLoader(context)
            val request = ImageRequest.Builder(context)
                .data(imageUrl)
                .allowHardware(false)
                .build()

            val result = loader.execute(request)
            return (result.drawable as BitmapDrawable).bitmap
        }

        private suspend fun loadSvgAsBitmap(context: Context, imageUrl: String): Bitmap {
            val loader = ImageLoader(context)
            val request = ImageRequest.Builder(context)
                .data(imageUrl)
                .decoderFactory(SvgDecoder.Factory())
                .build()

            val result = loader.execute(request)
            return (result.drawable as BitmapDrawable).bitmap
        }
    }
}