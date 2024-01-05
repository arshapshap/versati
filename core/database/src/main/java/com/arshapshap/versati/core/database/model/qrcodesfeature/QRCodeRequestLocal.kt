package com.arshapshap.versati.core.database.model.qrcodesfeature

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "QRCodeRequest")
data class QRCodeRequestLocal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "data") val data: String,
    @ColumnInfo(name = "size") val size: Int,
    @ColumnInfo(name = "color") val color: Int,
    @ColumnInfo(name = "background_color") val backgroundColor: Int,
    @ColumnInfo(name = "quiet_zone") val quietZone: Int,
    @ColumnInfo(name = "format") val format: String
)