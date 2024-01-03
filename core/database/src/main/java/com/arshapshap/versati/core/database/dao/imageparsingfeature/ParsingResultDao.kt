package com.arshapshap.versati.core.database.dao.imageparsingfeature

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.arshapshap.versati.core.database.model.imageparsingfeature.ParsingResultLocal

@Dao
abstract class ParsingResultDao {

    @Insert
    abstract suspend fun add(parsingResult: ParsingResultLocal): Long

    @Query("DELETE FROM ParsingResult WHERE id IN (SELECT id FROM ParsingResult ORDER BY id ASC LIMIT 1)")
    abstract suspend fun deleteOldest()

    @Query("SELECT * FROM ParsingResult")
    abstract suspend fun getAll(): List<ParsingResultLocal>

    @Query("SELECT COUNT(id) FROM PARSINGRESULT")
    abstract suspend fun getCount(): Int
}