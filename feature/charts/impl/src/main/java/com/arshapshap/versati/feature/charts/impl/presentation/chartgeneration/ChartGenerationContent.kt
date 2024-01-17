package com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arshapshap.versati.core.designsystem.theme.ButtonHeight
import com.arshapshap.versati.feature.charts.impl.R
import com.arshapshap.versati.feature.charts.impl.presentation.chartgeneration.contract.ChartGenerationState

@Composable
internal fun QRCodeGenerationContent(
    state: ChartGenerationState,
    viewModel: ChartGenerationViewModel
) {
    QRCodeGenerationContent(
        state = state
    )
}

@Composable
private fun QRCodeGenerationContent(
    state: ChartGenerationState,
    onCreateClick: () -> Unit = { },
    onShareClick: () -> Unit = { },
    onSuccessfulLoading: () -> Unit = { },
    advancedExpanded: MutableState<Boolean> = remember { mutableStateOf(false) }
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Button(
            onClick = { advancedExpanded.value = !advancedExpanded.value },
        ) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = stringResource(R.string.show_advanced_options),
                modifier = Modifier.rotate(if (advancedExpanded.value) 180f else 0f)
            )
            Text(text = stringResource(R.string.advanced_options))
        }
        if (advancedExpanded.value) {
            AdvancedOptions(
                state = state
            )
        }
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .height(ButtonHeight)
        ) {
            Button(
                onClick = onCreateClick,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(4f)
            ) {
                Text(
                    text = stringResource(R.string.create_chart),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            }
            if (state.success) {
                Spacer(modifier = Modifier.padding(4.dp))
                Button(
                    onClick = onShareClick,
                    modifier = Modifier
                        .aspectRatio(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share QR-code"
                    )
                }
            }
        }
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
private fun QRCodeGenerationContentPreview() {
    val state = ChartGenerationState()
    QRCodeGenerationContent(
        state = state,
        advancedExpanded = mutableStateOf(false)
    )
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
private fun QRCodeGenerationContentSuccessPreview() {
    val state = ChartGenerationState(
        success = true
    )
    QRCodeGenerationContent(
        state = state,
        advancedExpanded = mutableStateOf(false)
    )
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
private fun QRCodeGenerationContentExpandedPreview() {
    val state = ChartGenerationState(
        chartImageUrl = "https://www.1zoom.ru/big2/541/255095-Sepik.jpg",
    )
    QRCodeGenerationContent(
        state = state,
        advancedExpanded = mutableStateOf(true)
    )
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
private fun QRCodeGenerationContentExpandedSuccessPreview() {
    val state = ChartGenerationState(
        success = true
    )
    QRCodeGenerationContent(
        state = state,
        advancedExpanded = mutableStateOf(true)
    )
}