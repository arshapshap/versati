package com.arshapshap.versati.feature.imageparsing.impl.presentation.history.contract

import androidx.compose.runtime.Immutable
import com.arshapshap.versati.feature.imageparsing.api.domain.model.ParsingResult

@Immutable
internal data class ParsingHistoryState(
    val history: List<ParsingResult> = listOf(),
    val showDialogToConfirmClear: Boolean = false
)