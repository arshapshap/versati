package com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.arshapshap.versati.feature.qrcodes.api.domain.model.ImageFormat
import com.arshapshap.versati.feature.qrcodes.impl.R
import com.arshapshap.versati.feature.qrcodes.impl.presentation.common.ui.QRCodeImage
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.contract.QRCodeGenerationState
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.elements.ColorInput
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.elements.DataInput
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.elements.FormatInput
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.elements.QuietZoneInput
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.elements.SizeInput

@Composable
internal fun QRCodeGenerationContent(
    state: QRCodeGenerationState,
    viewModel: QRCodeGenerationViewModel
) {
    QRCodeGenerationContent(
        state = state,
        onDataChange = viewModel::updateData,
        onSizeChange = viewModel::updateSize,
        onQRCodeColorChange = viewModel::updateColor,
        onBackgroundColorChange = viewModel::updateBackgroundColor,
        onQuietZoneChange = viewModel::updateQuietZone,
        onFormatChange = viewModel::updateFormat,
        onCreateClick = viewModel::createQRCode,
        onShareClick = viewModel::shareQRCode,
        onSuccessfulLoading = viewModel::setSuccess
    )
}

@Composable
private fun QRCodeGenerationContent(
    state: QRCodeGenerationState,
    onDataChange: (String) -> Unit = { },
    onSizeChange: (String) -> Unit = { },
    onQRCodeColorChange: (String) -> Unit = { },
    onBackgroundColorChange: (String) -> Unit = { },
    onQuietZoneChange: (String) -> Unit = { },
    onFormatChange: (ImageFormat) -> Unit = { },
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
        QRCodeImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(vertical = 16.dp),
            imageUrl = state.qrCodeImageUrl,
            onSuccess = onSuccessfulLoading
        )
        DataInput(
            modifier = Modifier
                .padding(vertical = 8.dp),
            text = state.data,
            onValueChange = onDataChange,
            isError = state.showDataFieldError,
        )
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
                state = state,
                onSizeChange = onSizeChange,
                onQRCodeColorChange = onQRCodeColorChange,
                onBackgroundColorChange = onBackgroundColorChange,
                onQuietZoneChange = onQuietZoneChange,
                onFormatChange = onFormatChange
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
                    text = stringResource(R.string.create_qr_code),
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
    state: QRCodeGenerationState,
    onSizeChange: (String) -> Unit,
    onQRCodeColorChange: (String) -> Unit,
    onBackgroundColorChange: (String) -> Unit,
    onQuietZoneChange: (String) -> Unit,
    onFormatChange: (ImageFormat) -> Unit
) {
    SizeInputs(
        modifier = Modifier
            .padding(vertical = 8.dp),
        size = state.size?.toString() ?: "",
        onValueChange = onSizeChange,
    )
    ColorInputs(
        modifier = Modifier
            .padding(vertical = 8.dp),
        qrCodeColorString = state.qrCodeColorString,
        qrCodeColor = state.qrCodeColor,
        showQrCodeColorError = state.showColorFieldError,
        backgroundColorString = state.backgroundColorString,
        backgroundColor = state.backgroundColor,
        showBackgroundColorError = state.showBackgroundColorFieldError,
        onQRCodeColorChange = onQRCodeColorChange,
        onBackgroundColorChange = onBackgroundColorChange
    )
    QuietZoneInput(
        modifier = Modifier
            .padding(vertical = 8.dp),
        quietZone = state.quietZone,
        onValueChange = onQuietZoneChange
    )
    FormatInput(
        modifier = Modifier
            .padding(vertical = 8.dp),
        format = state.format,
        onValueChange = onFormatChange
    )
}

@Composable
private fun SizeInputs(
    modifier: Modifier,
    size: String,
    onValueChange: (String) -> Unit
) {
    Row(modifier = modifier) {
        SizeInput(
            modifier = Modifier.weight(1f),
            size = size,
            titleRes = R.string.width,
            iconRes = R.drawable.ic_width,
            onValueChange = onValueChange
        )
        SizeInput(
            modifier = Modifier.weight(1f),
            size = size,
            titleRes = R.string.height,
            iconRes = R.drawable.ic_height,
            onValueChange = onValueChange
        )
    }
}

@Composable
private fun ColorInputs(
    modifier: Modifier,
    qrCodeColorString: String,
    qrCodeColor: Int?,
    showQrCodeColorError: Boolean,
    backgroundColorString: String,
    backgroundColor: Int?,
    showBackgroundColorError: Boolean,
    onQRCodeColorChange: (String) -> Unit,
    onBackgroundColorChange: (String) -> Unit
) {
    Row(
        modifier = modifier
    ) {
        ColorInput(
            modifier = Modifier
                .weight(1f),
            iconRes = R.drawable.ic_qr_code,
            titleRes = R.string.qr_code_color,
            colorString = qrCodeColorString,
            color = qrCodeColor,
            isError = showQrCodeColorError,
            onValueChange = onQRCodeColorChange
        )
        ColorInput(
            modifier = Modifier
                .weight(1f),
            iconRes = R.drawable.ic_background_grid,
            titleRes = R.string.background_color,
            colorString = backgroundColorString,
            color = backgroundColor,
            isError = showBackgroundColorError,
            onValueChange = onBackgroundColorChange
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
private fun QRCodeGenerationContentPreview() {
    val state = QRCodeGenerationState()
    QRCodeGenerationContent(
        state = state,
        advancedExpanded = mutableStateOf(false)
    )
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
private fun QRCodeGenerationContentSuccessPreview() {
    val state = QRCodeGenerationState(
        data = "https://www.google.com/",
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
    val state = QRCodeGenerationState(
        data = "https://www.google.com/",
        qrCodeImageUrl = "https://www.1zoom.ru/big2/541/255095-Sepik.jpg",
        qrCodeColorString = "FF0000",
        qrCodeColor = 0xFF0000,
        backgroundColorString = "000000",
        backgroundColor = null,
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
    val state = QRCodeGenerationState(
        data = "https://www.google.com/",
        qrCodeColorString = "FF0000",
        qrCodeColor = 0xFF0000,
        backgroundColorString = "000000",
        backgroundColor = null,
        success = true
    )
    QRCodeGenerationContent(
        state = state,
        advancedExpanded = mutableStateOf(true)
    )
}