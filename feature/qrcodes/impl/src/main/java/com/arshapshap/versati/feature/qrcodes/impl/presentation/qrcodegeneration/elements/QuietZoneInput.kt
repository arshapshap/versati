package com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.elements

import androidx.compose.foundation.layout.fillMaxWidth
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
internal fun QuietZoneInput(
    modifier: Modifier,
    quietZone: Int?,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = quietZone?.toString() ?: "",
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_square),
                tint = MaterialTheme.colorScheme.outline,
                contentDescription = null
            )
        },
        modifier = modifier
            .fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(text = stringResource(R.string.quiet_zone)) },
        onValueChange = onValueChange,
    )
}

@Preview(showBackground = true)
@Composable
private fun QuietZoneInputPreview() {
    QuietZoneInput(
        modifier = Modifier,
        quietZone = 3,
        onValueChange = { }
    )
}