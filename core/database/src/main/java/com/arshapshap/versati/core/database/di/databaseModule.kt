package com.arshapshap.versati.core.database.di

import android.content.Context
import com.arshapshap.versati.core.database.AppDatabase
import com.arshapshap.versati.core.database.dao.chartsfeature.ChartDao
import com.arshapshap.versati.core.database.dao.imageparsingfeature.ParsingResultDao
import com.arshapshap.versati.core.database.dao.qrcodesfeature.QRCodeRequestDao
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> { AppDatabase.get(get<Context>()) }
    single<ChartDao> { get<AppDatabase>().chartDao() }
    single<ParsingResultDao> { get<AppDatabase>().parsingResultDao() }
    single<QRCodeRequestDao> { get<AppDatabase>().qrCodeRequestDao() }
}