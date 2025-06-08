cmake_minimum_required(VERSION 3.22.1)
project("unitconverterpro")

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

add_library(
        ${PROJECT_NAME}
        SHARED
        converter/Converter.cpp
        jni/NativeBridge.cpp
)

target_include_directories(
        ${PROJECT_NAME}
        PRIVATE
        ${CMAKE_CURRENT_SOURCE_DIR}/converter
        ${CMAKE_CURRENT_SOURCE_DIR}/jni
)

find_library(
        log-lib
        log
)

target_link_libraries(
        ${PROJECT_NAME}
        android
        ${log-lib}
)