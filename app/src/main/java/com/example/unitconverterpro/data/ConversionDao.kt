package com.example.unitconverterpro.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversionDao {
    @Insert
    fun insert(conversion: ConversionEntity)

    @Query("SELECT * FROM conversion_history ORDER BY timestamp DESC")
    fun getAll(): Flow<List<ConversionEntity>>
}