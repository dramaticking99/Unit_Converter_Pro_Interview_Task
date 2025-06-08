package com.example.unitconverterpro.ui.models

enum class UnitCategory (val displayName: String, val units: List<String>) {
    Length("Length", listOf("Meter", "Kilometer", "Inch", "Mile")),
    Temperature("Temperature", listOf("Celsius", "Fahrenheit", "Kelvin")),
    Weight("Weight", listOf("Kilogram", "Gram", "Pound"))
}