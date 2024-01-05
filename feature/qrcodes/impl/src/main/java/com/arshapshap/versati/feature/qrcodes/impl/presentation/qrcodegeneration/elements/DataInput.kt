package com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.arshapshap.versati.feature.qrcodes.impl.R

@Composable
internal fun DataInput(
    modifier: Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    isError: Boolean
) {
    OutlinedTextField(
        value = text,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Info,
                tint = MaterialTheme.colorScheme.outline,
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
        label = { Text(text = stringResource(R.string.data)) },
        placeholder = { Text(text = stringResource(R.string.data_hint)) },
        onValueChange = onValueChange,
        isError = isError
    )
}

@Preview(showBackground = true)
@Composable
private fun DataInputPreview() {
    DataInput(
        modifier = Modifier,
        text = "https://google.com",
        isError = false,
        onValueChange = { }
    )
}