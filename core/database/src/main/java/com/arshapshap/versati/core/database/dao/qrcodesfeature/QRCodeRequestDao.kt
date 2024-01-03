package com.arshapshap.versati.core.database.dao.qrcodesfeature

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.arshapshap.versati.core.database.model.qrcodesfeature.QRCodeRequestLocal

@Dao
abstract class QRCodeRequestDao {

    @Insert
    abstract suspend fun add(requestDao: QRCodeRequestLocal): Long

    @Query("DELETE FROM QRCodeRequest WHERE id IN (SELECT id FROM QRCodeRequest ORDER BY id ASC LIMIT 1)")
    abstract suspend fun deleteOldest()

    @Query("SELECT * FROM QRCodeRequest")
    abstract suspend fun getAll(): List<QRCodeRequestLocal>

    @Query("SELECT COUNT(id) FROM QRCodeRequest")
    abstract suspend fun getCount(): Int
}