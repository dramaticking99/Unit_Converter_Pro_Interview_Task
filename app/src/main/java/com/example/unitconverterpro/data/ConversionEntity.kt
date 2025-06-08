package com.example.unitconverterpro.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversion_history")
data class ConversionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val category: String,
    val fromUnit: String,
    val toUnit: String,
    val inputValue: Double,
    val resultValue: Double,
    val timestamp: Long = System.currentTimeMillis()
)