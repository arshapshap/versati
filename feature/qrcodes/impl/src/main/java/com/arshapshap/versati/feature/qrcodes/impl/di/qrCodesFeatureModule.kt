package com.arshapshap.versati.feature.qrcodes.impl.di

import com.arshapshap.versati.core.database.dao.qrcodesfeature.QRCodeRequestDao
import com.arshapshap.versati.feature.qrcodes.api.domain.repository.QRCodesRepository
import com.arshapshap.versati.feature.qrcodes.api.domain.usecase.ClearHistoryUseCase
import com.arshapshap.versati.feature.qrcodes.api.domain.usecase.CreateQRCodeUseCase
import com.arshapshap.versati.feature.qrcodes.api.domain.usecase.GetQRCodeInfoByIdUseCase
import com.arshapshap.versati.feature.qrcodes.api.domain.usecase.GetQRCodesHistoryUseCase
import com.arshapshap.versati.feature.qrcodes.impl.data.mapper.QRCodesMapper
import com.arshapshap.versati.feature.qrcodes.impl.data.repository.QRCodesRepositoryImpl
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodegeneration.QRCodeGenerationViewModel
import com.arshapshap.versati.feature.qrcodes.impl.presentation.qrcodeshistory.QRCodesHistoryViewModel
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
    factory<ClearHistoryUseCase> { ClearHistoryUseCase(get<QRCodesRepository>()) }
    factory<CreateQRCodeUseCase> { CreateQRCodeUseCase(get<QRCodesRepository>()) }
    factory<GetQRCodeInfoByIdUseCase> { GetQRCodeInfoByIdUseCase(get<QRCodesRepository>()) }
    factory<GetQRCodesHistoryUseCase> { GetQRCodesHistoryUseCase(get<QRCodesRepository>()) }

    // Presentation
    factory<QRCodeGenerationViewModel> { (id: Long) ->
        QRCodeGenerationViewModel(id, get<CreateQRCodeUseCase>(), get<GetQRCodeInfoByIdUseCase>())
    }
    factory<QRCodesHistoryViewModel> {
        QRCodesHistoryViewModel(get<GetQRCodesHistoryUseCase>(), get<ClearHistoryUseCase>())
    }
}