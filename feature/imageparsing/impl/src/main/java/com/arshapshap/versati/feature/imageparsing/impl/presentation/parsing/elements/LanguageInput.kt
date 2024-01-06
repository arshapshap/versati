package com.arshapshap.versati.feature.imageparsing.impl.presentation.parsing.elements

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.arshapshap.versati.core.designsystem.elements.DropdownInput
import com.arshapshap.versati.feature.imageparsing.api.domain.model.Language
import com.arshapshap.versati.feature.imageparsing.impl.R

@Composable
internal fun LanguageInput(
    modifier: Modifier,
    language: Language,
    onValueChange: (Language) -> Unit
) {
    DropdownInput(
        modifier = modifier,
        valueString = language.name,
        onSelect = {
            onValueChange(Language.valueOf(it))
        },
        entries = Language.entries.map { it.name },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_language),
                tint = MaterialTheme.colorScheme.outline,
                contentDescription = null
            )
        },
        label = stringResource(R.string.language)
    )
}

@Preview(showBackground = true)
@Composable
private fun FormatInputPreview() {
    LanguageInput(
        modifier = Modifier,
        language = Language.English,
        onValueChange = { }
    )
}