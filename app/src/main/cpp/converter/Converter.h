//
// Created by Chetan Sanwariya on 08/06/25.
//

#ifndef UNIT_CONVERTER_PRO_CONVERTER_H
#define UNIT_CONVERTER_PRO_CONVERTER_H

#pragma once
#include<string>

enum class UnitType {
    Length = 0,
    Temperature = 1,
    Weight = 2
};

// Single Dispatcher
double convert(
        UnitType type,
        double value,
        const std::string& fromUnit,
        const std::string& toUnit
        );

#endif //UNIT_CONVERTER_PRO_CONVERTER_H
