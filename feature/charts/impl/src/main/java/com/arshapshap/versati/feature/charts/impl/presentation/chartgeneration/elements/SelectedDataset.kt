package com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arshapshap.versati.feature.charts.impl.R
import com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.contract.DatasetState

@Composable
internal fun SelectedDataset(
    dataset: DatasetState,
    index: Int,
    isError: Boolean,
    showDeleteButton: Boolean,
    onDatasetDelete: (Int) -> Unit
) {
    val buttonColor =
        if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
    OutlinedButton(
        onClick = { },
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = buttonColor
        ),
        border = ButtonDefaults.outlinedButtonBorder.copy(
            brush = SolidColor(buttonColor)
        )
    ) {
        Text(text = dataset.label.ifEmpty {
            stringResource(R.string.dataset_with_number, index + 1)
        })
        if (showDeleteButton) {
            Spacer(modifier = Modifier.padding(4.dp))
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = null,
                tint = buttonColor,
                modifier = Modifier.clickable { onDatasetDelete(index) }
            )
        }
    }
}