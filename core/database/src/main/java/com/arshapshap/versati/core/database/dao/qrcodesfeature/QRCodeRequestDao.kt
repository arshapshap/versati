package com.arshapshap.versati.core.database.dao.qrcodesfeature

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.arshapshap.versati.core.database.model.qrcodesfeature.QRCodeRequestLocal

@Dao
abstract class QRCodeRequestDao {

    @Insert
    abstract suspend fun saveQRCodeRequest(requestDao: QRCodeRequestLocal): Long

    @Query("DELETE FROM QRCodeRequest WHERE qr_code_request_id = :id")
    abstract suspend fun deleteQRCodeRequest(id: Long)

    @Query("SELECT * FROM QRCodeRequest")
    abstract suspend fun getQRCodeRequestHistory(): List<QRCodeRequestLocal>
}