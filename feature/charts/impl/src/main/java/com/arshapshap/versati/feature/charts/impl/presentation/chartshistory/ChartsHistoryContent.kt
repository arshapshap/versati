package com.arshapshap.versati.feature.charts.impl.presentation.chartshistory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arshapshap.versati.core.designsystem.elements.ConfirmationAlertDialog
import com.arshapshap.versati.feature.charts.api.model.ChartInfo
import com.arshapshap.versati.feature.charts.api.model.ChartType
import com.arshapshap.versati.feature.charts.api.model.Dataset
import com.arshapshap.versati.feature.charts.impl.R
import com.arshapshap.versati.feature.charts.impl.presentation.chartshistory.contract.ChartsHistoryState
import com.arshapshap.versati.feature.charts.impl.presentation.common.ui.ChartImage

@Composable
internal fun ChartsHistoryContent(
    state: ChartsHistoryState,
    viewModel: ChartsHistoryViewModel
) {
    ChartsHistoryContent(
        state = state,
        onChartClick = viewModel::openChart,
        onClearHistory = viewModel::clearHistoryConfirmed,
        onCancelClearing = viewModel::cancelClear
    )
}

@Composable
private fun ChartsHistoryContent(
    state: ChartsHistoryState,
    onChartClick: (Long) -> Unit = { },
    onClearHistory: () -> Unit = { },
    onCancelClearing: () -> Unit = { }
) {
    if (state.showDialogToConfirmClear) {
        ConfirmationAlertDialog(
            onDismissRequest = { onCancelClearing() },
            onConfirmation = { onClearHistory() },
            dialogTitle = stringResource(R.string.clearing_the_history),
            dialogText = stringResource(R.string.do_you_really_want_to_clear_the_history),
            icon = Icons.Default.Delete
        )
    }
    if (state.history.isEmpty()) {
        Text(
            text = stringResource(R.string.charts_history_empty),
            modifier = Modifier
                .padding(16.dp),
            style = MaterialTheme.typography.titleMedium
        )
    } else {
        LazyColumn {
            items(state.history) {
                ChartInfo(
                    chartInfo = it,
                    onClick = onChartClick
                )
            }
        }
    }
}

@Composable
private fun ChartInfo(
    chartInfo: ChartInfo,
    onClick: (Long) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick(chartInfo.id) }
        .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .height(70.dp)
        ) {
            ChartInfoText(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(end = 16.dp),
                chartInfo = chartInfo
            )
            ChartImage(
                modifier = Modifier
                    .fillMaxHeight(),
                imageUrl = chartInfo.imageUrl
            )
        }
    }
}

@Composable
private fun ChartInfoText(
    modifier: Modifier,
    chartInfo: ChartInfo
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = getChartLabelsString(chartInfo),
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1
        )
        Text(
            text = getChartDatasetsString(chartInfo),
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1
        )
        Text(
            text = getChartTypeString(chartInfo),
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1
        )
    }
}

@Composable
private fun getChartLabelsString(chartInfo: ChartInfo): String {
    return stringResource(
        id = R.string.chart_description_labels,
        chartInfo.xAxisLabels.joinToString(", ")
    )
}

@Composable
private fun getChartDatasetsString(chartInfo: ChartInfo): String {
    return stringResource(
        id = R.string.chart_description_datasets,
        chartInfo.datasets.joinToString(", ") { it.label }
    )
}

@Composable
private fun getChartTypeString(chartInfo: ChartInfo): String {
    return stringResource(
        id = R.string.chart_description_type,
        chartInfo.type
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val state = ChartsHistoryState(
        List(5) {
            ChartInfo(
                id = 1,
                type = ChartType.Bar,
                xAxisLabels = "January,February,March,April,May".split(','),
                datasets = listOf(
                    Dataset(
                        label = "Users 1",
                        data = listOf(30, 60, 90, 120, 150),
                        borderColor = null,
                        borderWidth = null,
                        fill = null,
                        backgroundColor = null
                    ),
                    Dataset(
                        label = "Users 2",
                        data = listOf(40, 60, 80, 100, 120),
                        borderColor = null,
                        borderWidth = null,
                        fill = null,
                        backgroundColor = null
                    )
                ),
                width = null,
                height = null,
                backgroundColor = null,
                imageUrl = ""
            )
        }
    )
    ChartsHistoryContent(
        state = state,
        onChartClick = { }
    )
}

@Preview(showBackground = true)
@Composable
private fun HistoryClearPreview() {
    val state = ChartsHistoryState(
        listOf()
    )
    ChartsHistoryContent(
        state = state,
        onChartClick = { }
    )
}