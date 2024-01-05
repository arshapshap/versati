package com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.elements

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.arshapshap.versati.feature.qrcodes.impl.R

@Composable
internal fun SizeInput(
    modifier: Modifier,
    size: String,
    @StringRes titleRes: Int,
    @DrawableRes iconRes: Int,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = size,
        leadingIcon = {
            Icon(
                painter = painterResource(iconRes),
                tint = MaterialTheme.colorScheme.outline,
                contentDescription = null
            )
        },
        modifier = modifier,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(text = stringResource(titleRes)) },
        onValueChange = onValueChange
    )
}

@Preview(showBackground = true)
@Composable
private fun SizeInputPreview() {
    SizeInput(
        modifier = Modifier,
        size = "200",
        titleRes = R.string.width,
        iconRes = R.drawable.ic_width,
        onValueChange = { }
    )
}