package com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.elements

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.arshapshap.versati.feature.qrcodes.impl.R

@Composable
internal fun ColorInput(
    modifier: Modifier,
    @DrawableRes iconRes: Int,
    @StringRes titleRes: Int,
    colorString: String,
    color: Int?,
    isError: Boolean,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = colorString,
        leadingIcon = {
            Icon(
                painter = painterResource(iconRes),
                tint = color?.let { Color(it.withAlpha()) }
                    ?: MaterialTheme.colorScheme.outline,
                contentDescription = null
            )
        },
        trailingIcon = {
            if (isError) Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null
            )
        },
        modifier = modifier
            .fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        label = { Text(text = stringResource(titleRes)) },
        isError = isError,
        onValueChange = onValueChange
    )
}

private fun Int.withAlpha() = this + 0xFF000000

@Preview(showBackground = true)
@Composable
private fun ColorInputPreview() {
    ColorInput(
        modifier = Modifier,
        iconRes = R.drawable.ic_qr_code,
        titleRes = R.string.qr_code_color,
        colorString = "ff0000",
        color = 0xff0000,
        isError = false,
        onValueChange = { }
    )
}