package com.arshapshap.versati.core.designsystem.elements

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmapOrNull
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest

@Composable
fun ImageWithLoading(
    modifier: Modifier,
    imageUrl: String,
    onSuccess: (Bitmap?) -> Unit,
    onError: () -> Unit,
    placeholderIcon: @Composable () -> Unit,
    errorIcon: @Composable () -> Unit,
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .build(),
        contentDescription = null,
        modifier = modifier
    ) {
        when {
            imageUrl.isEmpty() -> placeholderIcon.invoke()
            painter.state is AsyncImagePainter.State.Loading -> LoadingIndicator()
            painter.state is AsyncImagePainter.State.Error -> {
                onError()
                errorIcon.invoke()
            }

            painter.state is AsyncImagePainter.State.Success -> {
                onSuccess(painter.state.getBitmap())
                SubcomposeAsyncImageContent()
            }
        }
    }
}

private fun AsyncImagePainter.State.getBitmap() =
    (this as AsyncImagePainter.State.Success).result.drawable.toBitmapOrNull()

@Composable
private fun LoadingIndicator() {
    Box {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}