package com.arshapshap.versati.feature.imageparsing.impl.presentation.parsing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arshapshap.versati.core.designsystem.theme.ButtonHeight
import com.arshapshap.versati.feature.imageparsing.api.domain.model.ParsedImage
import com.arshapshap.versati.feature.imageparsing.api.domain.model.ParsingResult
import com.arshapshap.versati.feature.imageparsing.impl.R
import com.arshapshap.versati.feature.imageparsing.impl.presentation.parsing.contract.ParsingState
import com.arshapshap.versati.feature.imageparsing.impl.presentation.parsing.elements.DataInput

@Composable
internal fun ParsingContent(
    state: ParsingState,
    viewModel: ParsingViewModel
) {
    ParsingContent(
        state = state,
        onUrlChange = viewModel::updateUrl,
        onParseClick = viewModel::parseImage
    )
}

@Composable
private fun ParsingContent(
    state: ParsingState,
    onUrlChange: (String) -> Unit = { },
    onParseClick: () -> Unit = { }
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        DataInput(
            modifier = Modifier
                .padding(vertical = 8.dp),
            text = state.url,
            onValueChange = onUrlChange,
            isError = state.showUrlFieldError
        )
        Button(
            onClick = onParseClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(ButtonHeight),
            enabled = !state.loading
        ) {
            Text(
                text = stringResource(R.string.parse_image),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }
        if (state.loading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        if (state.parsingResult != null)
            Result(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                result = state.parsingResult
            )
    }
}

@Composable
private fun Result(
    modifier: Modifier,
    result: ParsingResult
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Result:",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        SelectionContainer {
            LazyColumn {
                items(result.parsedResults) {
                    Text(text = it.parsedText)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val state = ParsingState(
        parsingResult = ParsingResult(
            id = 1,
            parsedResults = listOf(
                ParsedImage(
                    parsedText = "LLalalallLLalalallaalLLalalallaalLLalalallaalLLalalallaalaal"
                )
            ),
            searchablePDFURL = "asd"
        )
    )
    ParsingContent(state = state)
}