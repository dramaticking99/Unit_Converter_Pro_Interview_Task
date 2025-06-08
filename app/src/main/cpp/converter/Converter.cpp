//
// Created by Chetan Sanwariya on 08/06/25.
//

#include "Converter.h"
#include <stdexcept>
#include <algorithm>

namespace {
    // Length Conversion
    static double convertLength(double v, const std::string& from, const std::string& to) {
        // 1) normalize to meters
        double meters = 0.0;
        if      (from == "Meter")     meters = v;
        else if (from == "Kilometer") meters = v * 1000.0;
        else if (from == "Inch")      meters = v * 0.0254;
        else if (from == "Mile")      meters = v * 1609.344;
        else throw std::invalid_argument("Unsupported length unit: " + from);

        // 2) convert meters → target
        if      (to == "Meter")     return meters;
        else if (to == "Kilometer") return meters / 1000.0;
        else if (to == "Inch")      return meters / 0.0254;
        else if (to == "Mile")      return meters / 1609.344;
        else throw std::invalid_argument("Unsupported length unit: " + to);
        return v;
    }

    // Temperature Conversion
    static double convertTemperature(double v, const std::string& from, const std::string& to) {
        // 1) normalize to Celsius
        double c = 0.0;
        if      (from == "Celsius")    c = v;
        else if (from == "Fahrenheit") c = (v - 32.0) * 5.0 / 9.0;
        else if (from == "Kelvin")     c = v - 273.15;
        else throw std::invalid_argument("Unsupported temperature unit: " + from);

        // 2) convert Celsius → target
        if      (to == "Celsius")    return c;
        else if (to == "Fahrenheit") return c * 9.0 / 5.0 + 32.0;
        else if (to == "Kelvin")     return c + 273.15;
        else throw std::invalid_argument("Unsupported temperature unit: " + to);
        return v;
    }

    // Weight Conversion
    static double convertWeight(double v,  const std::string& from, const std::string& to) {
        // 1) normalize to kilograms
        double kg = 0.0;
        if      (from == "Kilogram") kg = v;
        else if (from == "Gram")     kg = v / 1000.0;
        else if (from == "Pound")    kg = v * 0.45359237;
        else throw std::invalid_argument("Unsupported weight unit: " + from);

        // 2) convert kilograms → target
        if      (to == "Kilogram") return kg;
        else if (to == "Gram")     return kg * 1000.0;
        else if (to == "Pound")    return kg / 0.45359237;
        else throw std::invalid_argument("Unsupported weight unit: " + to);
        return v;
    }
}

double convert(UnitType type, double value, const std::string& fromUnit, const std::string& toUnit) {
    switch(type) {
        case UnitType::Length:      return convertLength(value, fromUnit, toUnit);
        case UnitType::Temperature: return convertTemperature(value, fromUnit, toUnit);
        case UnitType::Weight:      return convertWeight(value, fromUnit, toUnit);
        default:
            throw std::invalid_argument("Unknown UnitType");
    }
}