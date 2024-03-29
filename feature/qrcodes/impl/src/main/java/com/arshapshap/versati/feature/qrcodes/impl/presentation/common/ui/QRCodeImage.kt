package com.arshapshap.versati.feature.qrcodes.impl.presentation.common.ui

import android.graphics.Bitmap
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.arshapshap.versati.core.designsystem.elements.ImageWithLoading
import com.arshapshap.versati.feature.qrcodes.impl.R

@Composable
internal fun QRCodeImage(
    modifier: Modifier,
    imageUrl: String,
    onSuccess: (Bitmap?) -> Unit = { },
    onError: () -> Unit = { }
) {
    ImageWithLoading(
        modifier = modifier,
        imageUrl = imageUrl,
        onSuccess = onSuccess,
        onError = onError,
        placeholderIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_qr_code),
                contentDescription = null
            )
        },
        errorIcon = {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = stringResource(R.string.uploading_qr_code_error),
            )
        }
    )
}