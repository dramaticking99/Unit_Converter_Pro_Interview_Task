
# Unit Converter Pro
A simple Android app with native C++ code (NDK) to convert between various units (length, weight, temperature)
## Prerequisites
- Android Studio (Arctic Fox or later) with NDK and CMake support installed.
- Android SDK (installed via SDK Manager).
- Git (for version control).
## Getting Started
1. **Clone the repository**
 ```bash
 git clone https://github.com/your-username/UnitConverterPro.git
 cd UnitConverterPro
```
2. **Open in Android Studio**
 - Launch Android Studio.
 - Select **Open an existing project**.
 - Navigate to the project root and click **OK**.
 - Android Studio will sync Gradle and download the necessary components.
3. **Build the project**
 - Wait for Gradle sync to finish.
 - Click **Run** ■■ or use **Shift + F10** to build and install the app on an emulator/device.
4. **Run the app**
 - Choose a connected device or emulator.
 - The app will launch, displaying an input field, unit selectors, and a conversion button.
## How NDK is Used
- **Native Code Location**:
 - C++ source files are in `app/src/main/cpp/`.
 - `converter/Converter.cpp` and `Converter.h` implement conversion logic.
 - `jni/NativeBridge.cpp` and `NativeBridge.h` define JNI bridge functions.
- **CMake Configuration**:
 - `app/src/main/cpp/CMakeLists.txt` defines the native library (`libnative-lib.so`) build.
 - In `app/build.gradle.kts`:
 ```kotlin
 android {
 defaultConfig {
 externalNativeBuild {
 cmake {
 path = file("src/main/cpp/CMakeLists.txt")
 }
 }
 ndk {
 abiFilters += listOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
 }
 }
 externalNativeBuild {
 cmake {
 path = file("src/main/cpp/CMakeLists.txt")
    }
}
 }
 ```
- **JNI Bridge**:
 - `NativeBridge.kt` declares:
 ```kotlin
 external fun nativeConvert(
 type: Int,
 value: Double,
 fromUnit: String,
 toUnit: String
 ): Double
 ```
 - At runtime, `System.loadLibrary("native-lib")` loads the library built by CMake.
 - The JNI function `Java_com_example_unitconverterpro_NativeBridge_nativeConvert` in C++ calls `Convert`
 - ## Project Structure
```text
UnitConverterPro/
■■■ app/
■ ■■■ src/
■ ■ ■■■ main/
■ ■ ■ ■■■ cpp/ # Native C++ code
■ ■ ■ ■■■ java/ # Kotlin/Java source
■ ■ ■ ■■■ res/ # Resources (layouts, drawables, etc.)
■ ■ ■■■ test/ # Unit and instrumentation tests
■ ■■■ build.gradle.kts # Module Gradle script
■ ■■■ ...
■■■ build.gradle.kts # Project Gradle script
■■■ settings.gradle.kts
■■■ .gitignore
```
