package com.arshapshap.versati.feature.qrcodes.impl.presentation.requesthistory

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arshapshap.versati.core.designsystem.elements.ConfirmationAlertDialog
import com.arshapshap.versati.feature.qrcodes.api.domain.model.ImageFormat
import com.arshapshap.versati.feature.qrcodes.api.domain.model.QRCodeInfo
import com.arshapshap.versati.feature.qrcodes.impl.R
import com.arshapshap.versati.feature.qrcodes.impl.presentation.common.ui.QRCodeImage
import com.arshapshap.versati.feature.qrcodes.impl.presentation.requesthistory.contract.RequestHistoryState

@Composable
internal fun RequestHistoryContent(
    state: RequestHistoryState,
    viewModel: RequestHistoryViewModel
) {
    RequestHistoryContent(
        state = state,
        onQRCodeClick = viewModel::openQRCode,
        onClearHistory = viewModel::clearHistoryConfirmed,
        onCancelClearing = viewModel::cancelClear
    )
}

@Composable
private fun RequestHistoryContent(
    state: RequestHistoryState,
    onQRCodeClick: (Long) -> Unit = { },
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
            text = stringResource(R.string.the_history_of_qr_codes_is_empty),
            modifier = Modifier
                .padding(16.dp),
            style = MaterialTheme.typography.titleMedium
        )
    } else {
        LazyColumn {
            items(state.history) {
                QRCodeInfo(
                    qrCodeInfo = it,
                    onClick = onQRCodeClick
                )
            }
        }
    }
}

@Composable
private fun QRCodeInfo(
    qrCodeInfo: QRCodeInfo,
    onClick: (Long) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick(qrCodeInfo.id) }
        .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .height(70.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = qrCodeInfo.data,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(end = 16.dp),
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium
            )
            QRCodeImage(
                modifier = Modifier
                    .fillMaxHeight(),
                imageUrl = qrCodeInfo.imageUrl
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val state = RequestHistoryState(
        List(5) {
            QRCodeInfo(
                id = 1,
                data = "google.com",
                size = 200,
                color = 0x000000,
                backgroundColor = 0xffffff,
                quietZone = 1,
                format = ImageFormat.PNG,
                imageUrl = "qrcodeurl"
            )
        }
    )
    RequestHistoryContent(
        state = state,
        onQRCodeClick = { }
    )
}

@Preview(showBackground = true)
@Composable
private fun HistoryClearPreview() {
    val state = RequestHistoryState(
        listOf()
    )
    RequestHistoryContent(
        state = state,
        onQRCodeClick = { }
    )
}