package com.arshapshap.versati.feature.imageparsing.impl.presentation.parsing.contract

import androidx.compose.runtime.Immutable
import com.arshapshap.versati.feature.imageparsing.api.domain.model.Language
import com.arshapshap.versati.feature.imageparsing.api.domain.model.ParsingResult

@Immutable
internal data class ParsingState(
    val url: String = "",
    val showUrlFieldError: Boolean = false,
    val language: Language = Language.English,
    val parsingResult: ParsingResult? = null,
    val loading: Boolean = false
)