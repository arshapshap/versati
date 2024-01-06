package com.arshapshap.versati.feature.imageparsing.impl.presentation.parsing.contract

internal sealed interface ParsingSideEffect {

    data object AuthorizationError : ParsingSideEffect

    data object TimeoutError : ParsingSideEffect

    data object NetworkError : ParsingSideEffect

    data object ParsingError : ParsingSideEffect
}