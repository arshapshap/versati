package com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration

import android.text.TextUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arshapshap.versati.feature.qrcodes.api.domain.model.ImageFormat
import com.arshapshap.versati.feature.qrcodes.api.domain.model.QRCodeOptions
import com.arshapshap.versati.feature.qrcodes.api.domain.usecase.CreateQRCodeUseCase
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.contract.QRCodeGenerationSideEffect
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.contract.QRCodeGenerationState
import okhttp3.internal.toHexString
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import kotlin.math.min

private typealias IntentContext = SimpleSyntax<QRCodeGenerationState, QRCodeGenerationSideEffect>

internal class QRCodeGenerationViewModel(
    private val createQRCodeUseCase: CreateQRCodeUseCase
) : ContainerHost<QRCodeGenerationState, QRCodeGenerationSideEffect>, ViewModel() {

    override val container =
        viewModelScope
            .container<QRCodeGenerationState, QRCodeGenerationSideEffect>(QRCodeGenerationState())

    fun createQRCode() = intent {
        reduce { state.copy(qrCodeImageUrl = "", success = false) }
        if (!checkIfOptionsValid()) return@intent

        val result = createQRCodeUseCase(getQRCodeOptions())
        reduce { state.copy(qrCodeImageUrl = result) }
    }

    fun shareQRCode() = intent {
        postSideEffect(QRCodeGenerationSideEffect.ShareQRCode(state.qrCodeImageUrl, state.format))
    }

    fun navigateToRequestHistory() = intent {
        postSideEffect(QRCodeGenerationSideEffect.NavigateToRequestHistory)
    }

    fun setSuccess() = intent {
        reduce { state.copy(success = true) }
    }

    @OptIn(OrbitExperimental::class)
    fun updateData(data: String) = blockingIntent {
        reduce { state.copy(data = data) }
    }

    @OptIn(OrbitExperimental::class)
    fun updateSize(size: String) = blockingIntent {
        reduce { state.copy(size = size.toIntOrNull()) }
    }

    @OptIn(OrbitExperimental::class)
    fun updateColor(color: String) = blockingIntent {
        reduce {
            state.copy(
                qrCodeColorString = color,
                qrCodeColor = color.toIntOrNull(16)
            )
        }
    }

    @OptIn(OrbitExperimental::class)
    fun updateBackgroundColor(color: String) = blockingIntent {
        reduce {
            state.copy(
                backgroundColorString = color,
                backgroundColor = color.toIntOrNull(16)
            )
        }
    }

    @OptIn(OrbitExperimental::class)
    fun updateQuietZone(quietZone: String) = blockingIntent {
        reduce { state.copy(quietZone = quietZone.toIntOrNull()) }
    }

    @OptIn(OrbitExperimental::class)
    fun updateFormat(format: ImageFormat) = blockingIntent {
        reduce { state.copy(format = format) }
    }

    private suspend fun IntentContext.checkIfOptionsValid(): Boolean {
        reduce {
            state.copy(
                size = validateSize(state.size, state.format),
                showDataFieldError = TextUtils.isEmpty(state.data),
                qrCodeColorString = state.qrCodeColor?.toHexString()?.padStart(6, '0')
                    ?: state.qrCodeColorString,
                showColorFieldError = state.qrCodeColor == null,
                backgroundColorString = state.backgroundColor?.toHexString()?.padStart(6, '0')
                    ?: state.backgroundColorString,
                showBackgroundColorFieldError = state.backgroundColor == null,
                quietZone = validateQuietZone(state.quietZone)
            )
        }
        return !state.showDataFieldError && !state.showColorFieldError && !state.showBackgroundColorFieldError
    }

    private suspend fun IntentContext.getQRCodeOptions() = QRCodeOptions(
        id = 0,
        data = state.data,
        size = state.size ?: 200,
        color = state.qrCodeColor ?: 0x000000,
        backgroundColor = state.backgroundColor ?: 0xFFFFFF,
        quietZone = state.quietZone ?: 0,
        format = state.format
    )

    private fun validateSize(size: Int?, format: ImageFormat): Int {
        if (size == null || size < 10)
            return 10
        return min(size, 1000)
    }

    private fun validateQuietZone(quietZone: Int?): Int {
        if (quietZone == null)
            return 0
        return min(quietZone, 100)
    }
}