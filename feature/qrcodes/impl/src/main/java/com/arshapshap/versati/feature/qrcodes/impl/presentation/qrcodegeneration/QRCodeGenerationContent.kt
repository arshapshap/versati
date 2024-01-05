package com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.arshapshap.versati.feature.qrcodes.api.domain.model.ImageFormat
import com.arshapshap.versati.feature.qrcodes.impl.R
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.contract.QRCodeGenerationState

@Composable
internal fun QRCodeGenerationContent(
    state: QRCodeGenerationState,
    screenModel: QRCodeGenerationScreenModel
) {
    QRCodeGenerationContent(
        state = state,
        onDataChange = screenModel::updateData,
        onSizeChange = screenModel::updateSize,
        onQRCodeColorChange = screenModel::updateColor,
        onBackgroundColorChange = screenModel::updateBackgroundColor,
        onQuietZoneChange = screenModel::updateQuietZone,
        onFormatChange = screenModel::updateFormat,
        onCreateClick = screenModel::createQRCode,
        onShareClick = screenModel::shareQRCode,
        onSuccessfulLoading = screenModel::setSuccess
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
    onSuccessfulLoading: () -> Unit = { }
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
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
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .height(70.dp)
        ) {
            Button(
                onClick = onCreateClick,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(4f)
            ) {
                Text(
                    text = stringResource(R.string.create_qr_code),
                    style = MaterialTheme.typography.headlineSmall
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
private fun QRCodeImage(
    modifier: Modifier,
    imageUrl: String,
    onSuccess: () -> Unit
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .decoderFactory(SvgDecoder.Factory())
            .build(),
        contentDescription = null,
        modifier = modifier
    ) {
        val state = painter.state
        if (state is AsyncImagePainter.State.Loading)
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        else if (state is AsyncImagePainter.State.Error && imageUrl != "")
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = stringResource(R.string.uploading_qr_code_error),
            )
        else if (state is AsyncImagePainter.State.Error)
            Icon(
                painter = painterResource(R.drawable.ic_qr_code),
                contentDescription = stringResource(R.string.qr_code)
            )
        else {
            onSuccess()
            SubcomposeAsyncImageContent()
        }
    }
}

@Composable
private fun DataInput(
    modifier: Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    isError: Boolean
) {
    OutlinedTextField(
        value = text,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Info,
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
        modifier = modifier
            .fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        label = { Text(text = stringResource(R.string.data)) },
        placeholder = { Text(text = stringResource(R.string.data_hint)) },
        onValueChange = onValueChange,
        isError = isError
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
            text = size,
            titleRes = R.string.width,
            iconRes = R.drawable.ic_width,
            onValueChange = onValueChange
        )
        SizeInput(
            modifier = Modifier.weight(1f),
            text = size,
            titleRes = R.string.height,
            iconRes = R.drawable.ic_height,
            onValueChange = onValueChange
        )
    }
}

@Composable
private fun SizeInput(
    modifier: Modifier,
    text: String,
    @StringRes titleRes: Int,
    @DrawableRes iconRes: Int,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = text,
        leadingIcon = {
            Icon(
                painterResource(iconRes),
                tint = MaterialTheme.colorScheme.outline,
                contentDescription = null
            )
        },
        modifier = modifier,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(text = stringResource(titleRes)) },
        onValueChange = onValueChange
    )
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

@Composable
private fun ColorInput(
    modifier: Modifier,
    @DrawableRes iconRes: Int,
    @StringRes titleRes: Int,
    colorString: String,
    color: Int?,
    isError: Boolean,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = colorString,
        leadingIcon = {
            Icon(
                painterResource(iconRes),
                tint = color?.let { Color(it.withAlpha()) }
                    ?: MaterialTheme.colorScheme.outline,
                contentDescription = null
            )
        },
        trailingIcon = {
            if (isError) Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null
            )
        },
        modifier = modifier
            .fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        label = { Text(text = stringResource(titleRes)) },
        isError = isError,
        onValueChange = onValueChange
    )
}

private fun Int.withAlpha() = this + 0xFF000000

@Composable
private fun QuietZoneInput(
    modifier: Modifier,
    quietZone: Int?,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = quietZone?.toString() ?: "",
        leadingIcon = {
            Icon(
                painterResource(R.drawable.ic_square),
                tint = MaterialTheme.colorScheme.outline,
                contentDescription = null
            )
        },
        modifier = modifier
            .fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(text = stringResource(R.string.quiet_zone)) },
        onValueChange = onValueChange,
    )
}

@Composable
private fun FormatInput(
    modifier: Modifier,
    format: ImageFormat,
    onValueChange: (ImageFormat) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = format.name,
        leadingIcon = {
            Icon(
                painterResource(R.drawable.ic_file),
                tint = MaterialTheme.colorScheme.outline,
                contentDescription = null
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = true },
        singleLine = true,
        label = { Text(text = stringResource(R.string.image_format)) },
        onValueChange = { },
        enabled = false,
        colors = getDefaultOutlinedTextInputColors()
    )
    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
        for (imageFormat in ImageFormat.entries) {
            DropdownMenuItem(
                text = { Text(imageFormat.name) },
                onClick = {
                    onValueChange(imageFormat)
                    expanded = false
                }
            )
        }
    }
}

@Composable
private fun getDefaultOutlinedTextInputColors() = OutlinedTextFieldDefaults.colors(
    disabledTextColor = MaterialTheme.colorScheme.onSurface,
    disabledContainerColor = Color.Transparent,
    disabledBorderColor = MaterialTheme.colorScheme.outline,
    disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
    disabledTrailingIconColor = MaterialTheme.colorScheme.onSurface,
    disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
    disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
    disabledSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
    disabledPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant,
    disabledSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant
)

@Preview(showBackground = true)
@Composable
private fun QRCodeGenerationContentPreview() {
    val state = QRCodeGenerationState(
        data = "https://www.google.com/",
        qrCodeImageUrl = "https://www.1zoom.ru/big2/541/255095-Sepik.jpg",
        qrCodeColorString = "FF0000",
        qrCodeColor = 0xFF0000,
        backgroundColorString = "000000",
        backgroundColor = null,
    )
    QRCodeGenerationContent(
        state = state
    )
}