package com.arshapshap.versati.feature.imageparsing.impl.presentation.history

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
import com.arshapshap.versati.feature.imageparsing.api.domain.model.ParsedImage
import com.arshapshap.versati.feature.imageparsing.api.domain.model.ParsingResult
import com.arshapshap.versati.feature.imageparsing.impl.R
import com.arshapshap.versati.feature.imageparsing.impl.presentation.history.contract.ParsingHistoryState

@Composable
internal fun ParsingHistoryContent(
    state: ParsingHistoryState,
    viewModel: ParsingHistoryViewModel
) {
    ParsingHistoryContent(
        state = state,
        onParsingClick = viewModel::openParsingResult,
        onClearHistory = viewModel::clearHistoryConfirmed,
        onCancelClearing = viewModel::cancelClear
    )
}

@Composable
private fun ParsingHistoryContent(
    state: ParsingHistoryState,
    onParsingClick: (Long) -> Unit = { },
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
            text = stringResource(R.string.parsing_history_empty),
            modifier = Modifier
                .padding(16.dp),
            style = MaterialTheme.typography.titleMedium
        )
    } else {
        LazyColumn {
            items(state.history) {
                ParsingResult(
                    parsingResult = it,
                    onClick = onParsingClick
                )
            }
        }
    }
}

@Composable
private fun ParsingResult(
    parsingResult: ParsingResult,
    onClick: (Long) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick(parsingResult.id) }
        .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .height(70.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = parsingResult.parsedResults[0].parsedText
                    .replace("\n", " "),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(end = 16.dp),
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val state = ParsingHistoryState(
        List(5) {
            ParsingResult(
                id = 1,
                parsedResults = listOf(
                    ParsedImage(
                        parsedText = "LLalalallLLalalallaalLLalalallaalLLalalallaalLLalalallaalaal"
                    )
                ),
                searchablePDFURL = "asd",
                sourceUrl = "asd.com"
            )
        }
    )
    ParsingHistoryContent(
        state = state,
        onParsingClick = { }
    )
}

@Preview(showBackground = true)
@Composable
private fun HistoryClearPreview() {
    val state = ParsingHistoryState(
        listOf()
    )
    ParsingHistoryContent(
        state = state,
        onParsingClick = { }
    )
}