package com.arshapshap.versati.feature.imageparsing.impl.presentation.parsing

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arshapshap.versati.core.designsystem.elements.ButtonWithLoading
import com.arshapshap.versati.feature.imageparsing.api.domain.model.Language
import com.arshapshap.versati.feature.imageparsing.api.domain.model.ParsedImage
import com.arshapshap.versati.feature.imageparsing.api.domain.model.ParsingResult
import com.arshapshap.versati.feature.imageparsing.impl.R
import com.arshapshap.versati.feature.imageparsing.impl.presentation.parsing.contract.ParsingState
import com.arshapshap.versati.feature.imageparsing.impl.presentation.parsing.elements.DataInput
import com.arshapshap.versati.feature.imageparsing.impl.presentation.parsing.elements.LanguageInput
import com.arshapshap.versati.feature.imageparsing.impl.presentation.parsing.elements.ParsedImage

@Composable
internal fun ParsingContent(
    state: ParsingState,
    viewModel: ParsingViewModel
) {
    ParsingContent(
        state = state,
        onUrlChange = viewModel::updateUrl,
        onLanguageChange = viewModel::updateLanguage,
        onParseClick = viewModel::parseImage
    )
}

@Composable
private fun ParsingContent(
    state: ParsingState,
    onUrlChange: (String) -> Unit = { },
    onLanguageChange: (Language) -> Unit = { },
    onParseClick: () -> Unit = { }
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        DataInput(
            modifier = Modifier
                .padding(vertical = 8.dp),
            text = state.url,
            onValueChange = onUrlChange,
            isError = state.showUrlFieldError
        )
        LanguageInput(
            modifier = Modifier
                .padding(vertical = 8.dp),
            language = state.language,
            onValueChange = onLanguageChange
        )
        ButtonWithLoading(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            onClick = onParseClick,
            text = stringResource(R.string.parse_image),
            loading = state.loading
        )
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
            text = stringResource(R.string.result),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        SelectionContainer(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant,
                    shape = MaterialTheme.shapes.extraSmall
                )
                .padding(8.dp)
        ) {
            Column {
                for (parsedImage in result.parsedResults) {
                    Text(text = parsedImage.parsedText)
                }
            }
        }
        Text(
            text = pluralStringResource(R.plurals.parsed_images, result.parsedResults.size),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 8.dp)
        )
        ParsedImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            parsingResult = result
        )
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
        ),
        loading = true
    )
    ParsingContent(state = state)
}