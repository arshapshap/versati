package com.arshapshap.versati.feature.imageparsing.impl.presentation.history.contract

internal sealed interface ParsingHistorySideEffect {
    data class OpenParsingResult(
        val id: Long
    ) : ParsingHistorySideEffect
}