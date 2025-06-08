//
// Created by Chetan Sanwariya on 08/06/25.
//
#pragma once

#include <jni.h>
#include "../converter/Converter.h"   // for UnitType & convert()

#ifdef __cplusplus
extern "C" {
#endif

/**
 * public static external fun nativeConvert(
 *      type: Int, value: Double, fromUnit: String, toUnit: String
 * ): Double
 */
JNIEXPORT jdouble JNICALL
Java_com_example_unitconverterpro_NativeBridge_nativeConvert(
        JNIEnv*  env,
        jobject  /* thisObject */,
        jint     type,
        jdouble  value,
        jstring  fromUnit,
        jstring  toUnit
);

#ifdef __cplusplus
}
#endif