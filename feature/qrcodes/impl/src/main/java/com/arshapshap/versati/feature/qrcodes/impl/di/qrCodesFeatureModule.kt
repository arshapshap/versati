package com.arshapshap.versati.feature.qrcodes.impl.di

import com.arshapshap.versati.core.database.dao.qrcodesfeature.QRCodeRequestDao
import com.arshapshap.versati.feature.qrcodes.api.domain.repository.QRCodesRepository
import com.arshapshap.versati.feature.qrcodes.api.domain.usecase.CreateQRCodeUseCase
import com.arshapshap.versati.feature.qrcodes.api.domain.usecase.GetRequestHistoryUseCase
import com.arshapshap.versati.feature.qrcodes.impl.data.mapper.QRCodesMapper
import com.arshapshap.versati.feature.qrcodes.impl.data.repository.QRCodesRepositoryImpl
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.QRCodeGenerationViewModel
import org.koin.dsl.module

val qrCodesFeatureModule = module {
    // Data
    factory<QRCodesMapper> { QRCodesMapper() }
    factory<QRCodesRepository> {
        QRCodesRepositoryImpl(
            get<QRCodeRequestDao>(),
            get<QRCodesMapper>()
        )
    }

    // Domain
    factory<CreateQRCodeUseCase> { CreateQRCodeUseCase(get<QRCodesRepository>()) }
    factory<GetRequestHistoryUseCase> { GetRequestHistoryUseCase(get<QRCodesRepository>()) }

    // Presentation
    factory<QRCodeGenerationViewModel> { QRCodeGenerationViewModel(get<CreateQRCodeUseCase>()) }
}