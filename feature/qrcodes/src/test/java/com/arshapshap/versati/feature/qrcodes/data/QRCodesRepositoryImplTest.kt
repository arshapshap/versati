package com.arshapshap.versati.feature.qrcodes.data

import com.arshapshap.versati.feature.qrcodes.BuildConfig
import com.arshapshap.versati.feature.qrcodes.data.mapper.QRCodesMapper
import com.arshapshap.versati.feature.qrcodes.data.repository.QRCodesRepositoryImpl
import com.arshapshap.versati.feature.qrcodes.domain.model.ImageFormat
import com.arshapshap.versati.feature.qrcodes.domain.model.QRCodeOptions
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class QRCodesRepositoryImplTest {

    private lateinit var repository: QRCodesRepositoryImpl
    private lateinit var mapper: QRCodesMapper

    @BeforeEach
    fun setUp() {
        mapper = spyk(QRCodesMapper())
        repository = QRCodesRepositoryImpl(mapper)
    }

    @Test
    fun urlCreating_isCorrect() = runBlocking {
        // Arrange
        val expectedUrl = "${BuildConfig.GOQR_BASE_URL}create-qr-code/?data=https://github.com&size=500x500&color=767676&bgcolor=000000&qzone=2&format=png"
        val options = QRCodeOptions(
            data = "https://github.com",
            size = 500,
            color = "767676".toLong(radix = 16),
            backgroundColor = "000000".toLong(radix = 16),
            quietZone = 2,
            format = ImageFormat.PNG
        )

        // Act
        val result = repository.getQRCodeImageUrl(options)

        // Assert
        assert(result == expectedUrl)
    }
}