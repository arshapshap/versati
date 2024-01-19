package com.arshapshap.versati.feature.charts.impl.presentation.common.ui

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.arshapshap.versati.core.designsystem.elements.ImageWithLoading
import com.arshapshap.versati.feature.charts.impl.R

@Composable
internal fun ChartImage(
    modifier: Modifier,
    imageUrl: String,
    showHint: Boolean = false,
    onSuccess: (Bitmap?) -> Unit = { },
    onError: () -> Unit = { }
) {
    Box(modifier = modifier) {
        if (showHint)
            Text(
                text = stringResource(id = R.string.first_loading_hint),
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
            )
        ImageWithLoading(
            modifier = modifier,
            imageUrl = imageUrl,
            onSuccess = onSuccess,
            onError = onError,
            placeholderIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_chart_finance),
                    contentDescription = null
                )
            },
            errorIcon = {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = stringResource(R.string.uploading_chart_error),
                )
            }
        )
    }
}