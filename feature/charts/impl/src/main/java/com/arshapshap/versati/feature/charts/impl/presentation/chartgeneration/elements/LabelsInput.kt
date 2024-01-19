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
internal fun LabelsInput(
    modifier: Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    isError: Boolean
) {
    OutlinedTextField(
        value = text,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_labels),
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
        label = { Text(text = stringResource(R.string.x_axis_labels)) },
        placeholder = {
            Text(
                text = stringResource(R.string.x_axis_labels_example),
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
private fun LabelsInputPreview() {
    LabelsInput(
        modifier = Modifier,
        text = "2012,2013,2014,2015,2016",
        isError = false,
        onValueChange = { }
    )
}