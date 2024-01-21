package com.arshapshap.versati.core.database.model.imageparsingfeature

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ParsingResult")
data class ParsingResultLocal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "parsed_text") val parsedText: String,
    @ColumnInfo(name = "source_url", defaultValue = "") val sourceUrl: String,
    @ColumnInfo(name = "searchable_pdf_url") val searchablePDFURL: String
)