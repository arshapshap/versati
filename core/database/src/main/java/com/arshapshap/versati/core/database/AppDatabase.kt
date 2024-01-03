package com.arshapshap.versati.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arshapshap.versati.core.database.dao.imageparsingfeature.ParsingResultDao
import com.arshapshap.versati.core.database.dao.qrcodesfeature.QRCodeRequestDao
import com.arshapshap.versati.core.database.model.imageparsingfeature.ParsingResultLocal
import com.arshapshap.versati.core.database.model.qrcodesfeature.QRCodeRequestLocal

@Database(
    version = 1,
    entities = [
        ParsingResultLocal::class,
        QRCodeRequestLocal::class
    ],
    exportSchema = false
)
internal abstract class AppDatabase : RoomDatabase() {

    companion object {

        private var instance: AppDatabase? = null

        @Synchronized
        fun get(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "app.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }

    abstract fun parsingResultDao(): ParsingResultDao

    abstract fun qrCodeRequestDao(): QRCodeRequestDao
}