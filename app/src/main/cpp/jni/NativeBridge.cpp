//
// Created by Chetan Sanwariya on 08/06/25.
//

#include "NativeBridge.h"
#include <string>

// Helper to turn jstring → std::string
static std::string toStdString(JNIEnv* env, jstring jstr) {
    const char* chars = env->GetStringUTFChars(jstr, nullptr);
    std::string str(chars);
    env->ReleaseStringUTFChars(jstr, chars);
    return str;
}

extern "C" JNIEXPORT jdouble JNICALL
Java_com_example_unitconverterpro_NativeBridge_nativeConvert(
        JNIEnv*  env,
        jobject  /* thisObject */,
        jint     type,
        jdouble  value,
        jstring  fromUnit,
        jstring  toUnit
) {
    // 1. Marshal args
    UnitType uType = static_cast<UnitType>(type);
    std::string from = toStdString(env, fromUnit);
    std::string to   = toStdString(env, toUnit);

    // 2. Call into your C++ dispatcher
    double result = 0.0;
    try {
        result = convert(uType, value, from, to);
    } catch (const std::exception& e) {
        // on error, you could throw a Java exception, but here we’ll return NaN
        return std::numeric_limits<double>::quiet_NaN();
    }
    return result;
}

