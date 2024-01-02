package com.arshapshap.versati.feature.qrcodes.di

import com.arshapshap.versati.feature.qrcodes.data.mapper.QRCodesMapper
import com.arshapshap.versati.feature.qrcodes.data.repository.QRCodesRepositoryImpl
import com.arshapshap.versati.feature.qrcodes.domain.repository.QRCodesRepository
import org.koin.dsl.module

val qrCodesFeatureModule = module {
    // Data
    factory<QRCodesMapper> { QRCodesMapper() }
    factory<QRCodesRepository> { QRCodesRepositoryImpl(get<QRCodesMapper>()) }
}