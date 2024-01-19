package com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.elements

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arshapshap.versati.feature.charts.impl.R
import com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.contract.DatasetState

@Composable
internal fun UnselectedDataset(
    dataset: DatasetState,
    index: Int,
    isError: Boolean,
    onDatasetExpand: (Int) -> Unit
) {
    val buttonColor =
        if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
    val contentColor =
        if (isError) MaterialTheme.colorScheme.onError else MaterialTheme.colorScheme.onPrimary
    Button(
        onClick = { onDatasetExpand(index) },
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            containerColor = buttonColor
        )
    ) {
        Text(text = dataset.label.ifEmpty {
            stringResource(R.string.dataset_with_number, index + 1)
        })
        if (isError) {
            Spacer(modifier = Modifier.padding(4.dp))
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null,
                tint = contentColor
            )
        }
    }
}