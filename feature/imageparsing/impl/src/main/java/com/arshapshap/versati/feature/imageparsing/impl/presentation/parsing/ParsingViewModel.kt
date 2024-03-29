package com.arshapshap.versati.feature.imageparsing.impl.presentation.parsing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arshapshap.versati.feature.imageparsing.api.domain.model.Language
import com.arshapshap.versati.feature.imageparsing.api.domain.usecase.GetParsingResultByIdUseCase
import com.arshapshap.versati.feature.imageparsing.api.domain.usecase.ParseImageBitmapUseCase
import com.arshapshap.versati.feature.imageparsing.api.domain.usecase.ParseImageByUrlUseCase
import com.arshapshap.versati.feature.imageparsing.impl.presentation.parsing.contract.ParsingSideEffect
import com.arshapshap.versati.feature.imageparsing.impl.presentation.parsing.contract.ParsingState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

private typealias IntentContext = SimpleSyntax<ParsingState, ParsingSideEffect>

internal class ParsingViewModel(
    parsingResultId: Long,
    private val parseImageByUrlUseCase: ParseImageByUrlUseCase,
    private val parseImageBitmapUseCase: ParseImageBitmapUseCase,
    private val getParsingResultByIdUseCase: GetParsingResultByIdUseCase
) : ContainerHost<ParsingState, ParsingSideEffect>, ViewModel() {

    override val container =
        viewModelScope
            .container<ParsingState, ParsingSideEffect>(ParsingState())

    init {
        if (parsingResultId != 0L)
            loadParsingResult(parsingResultId)
    }

    fun parseImage() = intent {
        if (state.url.isEmpty()) {
            reduce { state.copy(showUrlFieldError = true) }
            return@intent
        }
        try {
            reduce { state.copy(parsingResult = null, loading = true) }
            val result = parseImageByUrlUseCase(state.url, state.language)

            if (result.ocrExitCode != 1) {
                reduce { state.copy(loading = false) }
                postSideEffect(ParsingSideEffect.ParsingError)
            } else {
                reduce { state.copy(parsingResult = result, loading = false) }
            }
        } catch (e: Exception) {
            reduce { state.copy(loading = false) }
            when (e) {
                is HttpException -> handleHttpException(e)
                is SocketTimeoutException -> postSideEffect(ParsingSideEffect.TimeoutError)
                is UnknownHostException -> postSideEffect(ParsingSideEffect.NetworkError)
            }
        }
    }

    @OptIn(OrbitExperimental::class)
    fun updateUrl(url: String) = blockingIntent {
        reduce { state.copy(url = url, showUrlFieldError = false) }
    }

    @OptIn(OrbitExperimental::class)
    fun updateLanguage(language: Language) = blockingIntent {
        reduce { state.copy(language = language) }
    }

    fun navigateToParsingHistory() = intent {
        postSideEffect(ParsingSideEffect.NavigateToHistory)
    }

    private suspend fun IntentContext.handleHttpException(e: HttpException) {
        when (e.code()) {
            403 -> postSideEffect(ParsingSideEffect.AuthorizationError)
        }
    }

    private fun loadParsingResult(id: Long) = intent {
        val parsingResult = getParsingResultByIdUseCase(id) ?: return@intent
        reduce {
            state.copy(
                parsingResult = parsingResult,
                url = parsingResult.sourceUrl
            )
        }
    }
}