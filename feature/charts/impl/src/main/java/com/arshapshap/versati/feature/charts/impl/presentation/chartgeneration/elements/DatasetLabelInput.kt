package com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.arshapshap.versati.feature.charts.impl.R

@Composable
internal fun DatasetLabelInput(
    modifier: Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    isError: Boolean
) {
    OutlinedTextField(
        value = text,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_abc),
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
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        label = { Text(text = stringResource(R.string.dataset_label)) },
        placeholder = { Text(text = stringResource(R.string.dataset_label_example)) },
        onValueChange = onValueChange,
        isError = isError
    )
}

@Preview(showBackground = true)
@Composable
private fun DatasetLabelInputPreview() {
    DatasetLabelInput(
        modifier = Modifier,
        text = "Users",
        isError = false,
        onValueChange = { }
    )
}