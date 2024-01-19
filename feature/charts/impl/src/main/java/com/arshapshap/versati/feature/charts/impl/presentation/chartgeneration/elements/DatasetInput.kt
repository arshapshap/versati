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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.arshapshap.versati.feature.charts.impl.R

@Composable
internal fun DatasetInput(
    modifier: Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    isError: Boolean
) {
    OutlinedTextField(
        value = text,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_bar_chart),
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
        label = { Text(text = stringResource(R.string.dataset)) }, placeholder = {
            Text(
                text = stringResource(R.string.dataset_example),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        },
        onValueChange = onValueChange,
        isError = isError
    )
}

@Preview(showBackground = true)
@Composable
private fun DatasetInputPreview() {
    DatasetInput(
        modifier = Modifier,
        text = "120,60,50,180,120",
        isError = false,
        onValueChange = { }
    )
}