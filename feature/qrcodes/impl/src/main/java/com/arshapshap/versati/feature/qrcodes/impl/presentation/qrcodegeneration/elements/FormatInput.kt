package com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.elements

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.arshapshap.versati.core.designsystem.elements.DropdownInput
import com.arshapshap.versati.feature.qrcodes.api.domain.model.ImageFormat
import com.arshapshap.versati.feature.qrcodes.impl.R

@Composable
internal fun FormatInput(
    modifier: Modifier,
    format: ImageFormat,
    onValueChange: (ImageFormat) -> Unit
) {
    DropdownInput(
        modifier = modifier,
        valueString = format.name,
        onSelect = {
            onValueChange(ImageFormat.valueOf(it))
        },
        entries = ImageFormat.entries.map { it.name },
        leadingIcon = {

            Icon(
                painter = painterResource(R.drawable.ic_file),
                tint = MaterialTheme.colorScheme.outline,
                contentDescription = null
            )
        },
        label = stringResource(R.string.image_format)
    )
}

@Preview(showBackground = true)
@Composable
private fun FormatInputPreview() {
    FormatInput(
        modifier = Modifier,
        format = ImageFormat.PNG,
        onValueChange = { }
    )
}