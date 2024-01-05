package com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.arshapshap.versati.feature.qrcodes.impl.R

@Composable
internal fun QRCodeImage(
    modifier: Modifier,
    imageUrl: String,
    onSuccess: () -> Unit
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .decoderFactory(SvgDecoder.Factory())
            .build(),
        contentDescription = null,
        modifier = modifier
    ) {
        val state = painter.state
        if (state is AsyncImagePainter.State.Loading)
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        else if (state is AsyncImagePainter.State.Error && imageUrl != "")
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = stringResource(R.string.uploading_qr_code_error),
            )
        else if (state is AsyncImagePainter.State.Error)
            Icon(
                painter = painterResource(R.drawable.ic_qr_code),
                contentDescription = stringResource(R.string.qr_code)
            )
        else {
            onSuccess()
            SubcomposeAsyncImageContent()
        }
    }
}