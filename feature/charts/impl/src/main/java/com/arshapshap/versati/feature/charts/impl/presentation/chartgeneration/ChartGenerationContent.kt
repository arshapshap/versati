package com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arshapshap.versati.core.designsystem.elements.ButtonWithLoading
import com.arshapshap.versati.core.designsystem.theme.ButtonHeight
import com.arshapshap.versati.feature.charts.impl.R
import com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.contract.ChartGenerationState
import com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.contract.DatasetState
import com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.elements.DatasetInput
import com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.elements.DatasetLabelInput
import com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.elements.LabelsInput
import com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.elements.SelectedDataset
import com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.elements.UnselectedDataset
import com.arshapshap.versati.feature.charts.impl.presentation.common.ui.ChartImage

@Composable
internal fun ChartGenerationContent(
    state: ChartGenerationState,
    viewModel: ChartGenerationViewModel
) {
    ChartGenerationContent(
        state = state,
        onLabelsChange = viewModel::updateLabels,
        onDatasetExpand = viewModel::expandDataset,
        onDatasetDelete = viewModel::deleteDataset,
        onDatasetCreate = viewModel::createDataset,
        onDatasetLabelChange = viewModel::updateDatasetLabel,
        onDatasetChange = viewModel::updateDataset,
        onCreateClick = viewModel::createChart,
        onShareClick = viewModel::shareChart,
        onImageLoadingSuccess = viewModel::onImageLoadingSuccess,
        onImageLoadingError = viewModel::onImageLoadingError
    )
}

@Composable
private fun ChartGenerationContent(
    state: ChartGenerationState,
    onLabelsChange: (String) -> Unit = { },
    onDatasetExpand: (Int) -> Unit = { },
    onDatasetDelete: (Int) -> Unit = { },
    onDatasetCreate: () -> Unit = { },
    onDatasetLabelChange: (Int, String) -> Unit = { _, _ -> },
    onDatasetChange: (Int, String) -> Unit = { _, _ -> },
    onCreateClick: () -> Unit = { },
    onShareClick: () -> Unit = { },
    onImageLoadingSuccess: (Bitmap?) -> Unit = { },
    onImageLoadingError: () -> Unit = { },
    advancedExpanded: MutableState<Boolean> = remember { mutableStateOf(false) }
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ChartImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(vertical = 16.dp),
            imageUrl = state.chartImageUrl,
            onSuccess = onImageLoadingSuccess,
            onError = onImageLoadingError
        )
        LabelsInput(
            modifier = Modifier.padding(vertical = 8.dp),
            text = state.labels,
            onValueChange = onLabelsChange,
            isError = state.showLabelsInputError
        )
        Datasets(
            state = state,
            onDatasetExpand = onDatasetExpand,
            onDatasetDelete = onDatasetDelete,
            onDatasetCreate = onDatasetCreate,
            onDatasetLabelChange = onDatasetLabelChange,
            onDatasetChange = onDatasetChange,
        )
//        Button(
//            onClick = { advancedExpanded.value = !advancedExpanded.value },
//        ) {
//            Icon(
//                imageVector = Icons.Default.ArrowDropDown,
//                contentDescription = stringResource(R.string.show_advanced_options),
//                modifier = Modifier.rotate(if (advancedExpanded.value) 180f else 0f)
//            )
//            Text(text = stringResource(R.string.advanced_options))
//        }
//        if (advancedExpanded.value) {
//            AdvancedOptions(
//                state = state
//            )
//        }
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .height(ButtonHeight)
        ) {
            ButtonWithLoading(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(4f),
                onClick = onCreateClick,
                text = stringResource(R.string.create_chart),
                loading = state.loading
            )
            if (state.success) {
                Spacer(modifier = Modifier.padding(4.dp))
                Button(
                    onClick = onShareClick,
                    modifier = Modifier
                        .aspectRatio(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = stringResource(R.string.share_chart)
                    )
                }
            }
        }
        if (state.loadingNumber == 1 && state.loading)
            Text(
                text = stringResource(id = R.string.first_loading_hint),
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
            )
    }
}

@Composable
private fun Datasets(
    state: ChartGenerationState,
    onDatasetExpand: (Int) -> Unit,
    onDatasetDelete: (Int) -> Unit,
    onDatasetCreate: () -> Unit,
    onDatasetLabelChange: (Int, String) -> Unit,
    onDatasetChange: (Int, String) -> Unit,
) {
    Column {
        LazyRow {
            itemsIndexed(state.datasets) { i, it ->
                if (state.expandedDataset == i) {
                    SelectedDataset(
                        dataset = it,
                        index = i,
                        isError = it.showDataInputError || it.showLabelInputError,
                        showDeleteButton = state.datasets.size > 1,
                        onDatasetDelete = onDatasetDelete
                    )
                } else {
                    UnselectedDataset(
                        dataset = it,
                        index = i,
                        isError = it.showDataInputError || it.showLabelInputError,
                        onDatasetExpand = onDatasetExpand
                    )
                }
                Spacer(modifier = Modifier.padding(4.dp))
            }
            item {
                IconButton(
                    onClick = { onDatasetCreate() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.add_dataset),
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
        DatasetOptions(
            state = state,
            onDatasetLabelChange = {
                onDatasetLabelChange(state.expandedDataset, it)
            },
            onDatasetChange = {
                onDatasetChange(state.expandedDataset, it)
            }
        )
    }
}

@Composable
private fun DatasetOptions(
    state: ChartGenerationState,
    onDatasetLabelChange: (String) -> Unit,
    onDatasetChange: (String) -> Unit,
) {
    Column {
        DatasetLabelInput(
            modifier = Modifier.padding(top = 8.dp),
            text = state.datasets[state.expandedDataset].label,
            onValueChange = onDatasetLabelChange,
            isError = state.datasets[state.expandedDataset].showLabelInputError
        )
        DatasetInput(
            modifier = Modifier.padding(vertical = 8.dp),
            text = state.datasets[state.expandedDataset].data,
            onValueChange = onDatasetChange,
            isError = state.datasets[state.expandedDataset].showDataInputError
        )
    }
}

@Composable
private fun AdvancedOptions(
    state: ChartGenerationState
) {

}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
private fun ChartGenerationContentPreview() {
    val state = ChartGenerationState(
        datasets = listOf(
            DatasetState(
                label = "Dataset 1"
            ),
            DatasetState(
                label = "lalala"
            ),
            DatasetState(
                label = ""
            )
        ),
        expandedDataset = 1
    )
    ChartGenerationContent(
        state = state,
        advancedExpanded = mutableStateOf(false)
    )
}