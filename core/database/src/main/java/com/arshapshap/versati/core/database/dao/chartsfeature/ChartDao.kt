package com.arshapshap.versati.core.database.dao.chartsfeature

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.arshapshap.versati.core.database.model.chartsfeature.ChartLocal

@Dao
abstract class ChartDao {

    @Insert
    abstract suspend fun add(chart: ChartLocal): Long

    @Query("DELETE FROM Chart WHERE id IN (SELECT id FROM Chart ORDER BY id ASC LIMIT 1)")
    abstract suspend fun deleteOldest()

    @Query("DELETE FROM Chart")
    abstract suspend fun deleteAll()

    @Query("SELECT * FROM Chart")
    abstract suspend fun getAll(): List<ChartLocal>

    @Query("SELECT COUNT(id) FROM Chart")
    abstract suspend fun getCount(): Int

    @Query("SELECT * FROM Chart WHERE id = :id")
    abstract suspend fun getById(id: Long): ChartLocal?
}