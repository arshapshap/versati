package com.arshapshap.versati.feature.qrcodes.di

import com.arshapshap.versati.core.database.dao.qrcodesfeature.QRCodeRequestDao
import com.arshapshap.versati.feature.qrcodes.data.mapper.QRCodesMapper
import com.arshapshap.versati.feature.qrcodes.data.repository.QRCodesRepositoryImpl
import com.arshapshap.versati.feature.qrcodes.domain.repository.QRCodesRepository
import com.arshapshap.versati.feature.qrcodes.domain.usecase.CreateQRCodeUseCase
import com.arshapshap.versati.feature.qrcodes.domain.usecase.GetRequestHistoryUseCase
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
}