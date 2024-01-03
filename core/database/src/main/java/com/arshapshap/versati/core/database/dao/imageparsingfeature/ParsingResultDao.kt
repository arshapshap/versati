package com.arshapshap.versati.core.database.dao.imageparsingfeature

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.arshapshap.versati.core.database.model.imageparsingfeature.ParsingResultLocal

@Dao
abstract class ParsingResultDao {

    @Insert
    abstract suspend fun saveParsingResult(parsingResult: ParsingResultLocal): Long

    @Query("DELETE FROM ParsingResult WHERE parsing_result_id = :id")
    abstract suspend fun deleteParsingResult(id: Long)

    @Query("SELECT * FROM ParsingResult")
    abstract suspend fun getParsingHistory(): List<ParsingResultLocal>
}