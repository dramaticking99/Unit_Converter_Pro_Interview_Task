package com.example.unitconverterpro

import android.app.Application
import com.example.unitconverterpro.data.AppDatabase

class UnitConverterApp : Application() {
    // lazily initialize the database singleton
    val database: AppDatabase by lazy {
        AppDatabase.getInstance(this)
    }
}