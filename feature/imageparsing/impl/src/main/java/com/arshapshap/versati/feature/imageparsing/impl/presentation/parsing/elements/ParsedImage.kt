package com.arshapshap.versati.feature.imageparsing.impl.presentation.parsing.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arshapshap.versati.feature.imageparsing.api.domain.model.ParsingResult
import com.rizzi.bouquet.HorizontalPDFReader
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.rememberHorizontalPdfReaderState

@Composable
internal fun ParsedImage(
    modifier: Modifier,
    parsingResult: ParsingResult
) {
    val pdfState = rememberHorizontalPdfReaderState(
        resource = ResourceType.Remote(parsingResult.searchablePDFURL)
    )
    Box {
        HorizontalPDFReader(
            state = pdfState,
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
        )
        if (parsingResult.parsedResults.size > 1) {
            Row(modifier = Modifier.padding(16.dp)) {
                if (pdfState.currentPage != 0)
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "You can scroll to the left",
                        tint = Color.Black
                    )
                Spacer(modifier = Modifier.weight(1f))
                if (pdfState.currentPage != parsingResult.parsedResults.size - 1)
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "You can scroll to the right",
                        tint = Color.Black
                    )
            }
        }
    }
}