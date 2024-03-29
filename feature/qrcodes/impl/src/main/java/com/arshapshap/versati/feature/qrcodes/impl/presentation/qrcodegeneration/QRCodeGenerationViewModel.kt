package com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration

import android.graphics.Bitmap
import android.text.TextUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arshapshap.versati.feature.qrcodes.api.domain.model.ImageFormat
import com.arshapshap.versati.feature.qrcodes.api.domain.model.QRCodeInfo
import com.arshapshap.versati.feature.qrcodes.api.domain.usecase.CreateQRCodeUseCase
import com.arshapshap.versati.feature.qrcodes.api.domain.usecase.GetQRCodeInfoByIdUseCase
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
    qrCodeInfoId: Long,
    private val createQRCodeUseCase: CreateQRCodeUseCase,
    private val getQRCodeInfoByIdUseCase: GetQRCodeInfoByIdUseCase
) : ContainerHost<QRCodeGenerationState, QRCodeGenerationSideEffect>, ViewModel() {

    override val container =
        viewModelScope
            .container<QRCodeGenerationState, QRCodeGenerationSideEffect>(QRCodeGenerationState())

    init {
        if (qrCodeInfoId != 0L)
            loadQRCodeInfo(qrCodeInfoId)
    }

    fun createQRCode() = intent {
        if (state.success && !state.optionsChanged) return@intent

        reduce { state.copy(qrCodeImageUrl = "", success = false) }
        if (!checkIfOptionsValid()) return@intent

        reduce { state.copy(loading = true) }
        val result = createQRCodeUseCase(getQRCodeOptions())
        reduce { state.copy(qrCodeImageUrl = result, optionsChanged = false) }
    }

    fun shareQRCode() = intent {
        postSideEffect(QRCodeGenerationSideEffect.ShareQRCode(state.bitmap, state.format))
    }

    fun navigateToQRCodesHistory() = intent {
        postSideEffect(QRCodeGenerationSideEffect.NavigateToQRCodesHistory)
    }

    fun onImageLoadingSuccess(bitmap: Bitmap?) = intent {
        reduce { state.copy(success = true, loading = false, bitmap = bitmap) }
    }

    fun onImageLoadingError() = intent {
        reduce { state.copy(success = false, loading = false) }
    }


    @OptIn(OrbitExperimental::class)
    fun expandAdvancedOptions() = blockingIntent {
        reduce { state.copy(advancedOptionsExpanded = !state.advancedOptionsExpanded) }
    }

    @OptIn(OrbitExperimental::class)
    fun updateData(data: String) = blockingIntent {
        reduce { state.copy(data = data, optionsChanged = true) }
    }

    @OptIn(OrbitExperimental::class)
    fun updateSize(size: String) = blockingIntent {
        reduce { state.copy(size = size.toIntOrNull(), optionsChanged = true) }
    }

    @OptIn(OrbitExperimental::class)
    fun updateColor(color: String) = blockingIntent {
        reduce {
            state.copy(
                qrCodeColorString = color,
                qrCodeColor = color.toIntOrNull(16),
                optionsChanged = true
            )
        }
    }

    @OptIn(OrbitExperimental::class)
    fun updateBackgroundColor(color: String) = blockingIntent {
        reduce {
            state.copy(
                backgroundColorString = color,
                backgroundColor = color.toIntOrNull(16),
                optionsChanged = true
            )
        }
    }

    @OptIn(OrbitExperimental::class)
    fun updateQuietZone(quietZone: String) = blockingIntent {
        reduce { state.copy(quietZone = quietZone.toIntOrNull(), optionsChanged = true) }
    }

    @OptIn(OrbitExperimental::class)
    fun updateFormat(format: ImageFormat) = blockingIntent {
        reduce { state.copy(format = format, optionsChanged = true) }
    }

    private fun loadQRCodeInfo(id: Long) = intent {
        val qrCodeInfoById = getQRCodeInfoByIdUseCase(id) ?: return@intent
        reduce {
            state.copy(
                data = qrCodeInfoById.data,
                size = qrCodeInfoById.size,
                qrCodeColor = qrCodeInfoById.color,
                backgroundColor = qrCodeInfoById.backgroundColor,
                quietZone = qrCodeInfoById.quietZone,
                format = qrCodeInfoById.format,
                qrCodeImageUrl = qrCodeInfoById.imageUrl,
                optionsChanged = false
            )
        }
    }

    private suspend fun IntentContext.checkIfOptionsValid(): Boolean {
        reduce {
            state.copy(
                size = validateSize(state.size),
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

    private fun IntentContext.getQRCodeOptions() = QRCodeInfo(
        id = 0,
        data = state.data,
        size = state.size ?: 200,
        color = state.qrCodeColor ?: 0x000000,
        backgroundColor = state.backgroundColor ?: 0xFFFFFF,
        quietZone = state.quietZone ?: 0,
        format = state.format,
        imageUrl = ""
    )

    private fun validateSize(size: Int?): Int {
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