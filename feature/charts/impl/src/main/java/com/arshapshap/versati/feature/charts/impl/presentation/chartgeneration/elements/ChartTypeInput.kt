package com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.elements

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.arshapshap.versati.core.designsystem.elements.DropdownInput
import com.arshapshap.versati.feature.charts.api.model.ChartType
import com.arshapshap.versati.feature.charts.impl.R

@Composable
internal fun ChartTypeInput(
    modifier: Modifier,
    chartType: ChartType,
    onValueChange: (ChartType) -> Unit
) {
    DropdownInput(
        modifier = modifier,
        valueString = chartType.name,
        onSelect = {
            onValueChange(ChartType.valueOf(it))
        },
        entries = ChartType.entries.map { it.name },
        leadingIcon = {

            Icon(
                painter = painterResource(getChartIcon(chartType)),
                tint = MaterialTheme.colorScheme.outline,
                contentDescription = null
            )
        },
        label = stringResource(R.string.chart_type)
    )
}

private fun getChartIcon(chartType: ChartType): Int {
    return when (chartType) {
        ChartType.Bar -> R.drawable.ic_bar_chart
        ChartType.Line -> R.drawable.ic_line_chart
        ChartType.Pie -> R.drawable.ic_pie_chart
        ChartType.Radar -> R.drawable.ic_radar_chart
    }
}

@Preview(showBackground = true)
@Composable
private fun ChartTypeInputPreview() {
    ChartTypeInput(
        modifier = Modifier,
        chartType = ChartType.Bar,
        onValueChange = { }
    )
}