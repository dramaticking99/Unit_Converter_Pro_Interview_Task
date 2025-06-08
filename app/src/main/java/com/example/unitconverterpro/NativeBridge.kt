package com.example.unitconverterpro

object NativeBridge {
    init {
        // must match your CMake target name: libunitconverterpro.so
        System.loadLibrary("unitconverterpro")
    }

    /**
     * @param type   The UnitType ordinal (0=Length,1=Temperature,2=Weight)
     * @param value  The input number
     * @param fromUnit  e.g. "Meter"
     * @param toUnit    e.g. "Kilometer"
     * @return converted Double or NaN on error
     */
    external fun nativeConvert(
        type: Int,
        value: Double,
        fromUnit: String,
        toUnit: String
    ): Double
}