// File: ui/theme/Color.kt
package com.example.unitconverterpro.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// brand colors
val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200   = Color(0xFF03DAC5)

// Light theme ColorScheme (unchanged)
private val LightColorScheme = lightColorScheme(
    primary            = Purple500,
    onPrimary          = Color.White,
    primaryContainer   = Purple700,
    onPrimaryContainer = Color.White,

    secondary            = Teal200,
    onSecondary          = Color.Black,
    secondaryContainer   = Teal200.copy(alpha = 0.3f),
    onSecondaryContainer = Color.Black,

    background         = Color(0xFFFFFFFF),
    onBackground       = Color.Black,

    surface            = Color(0xFFFFFFFF),
    onSurface          = Color.Black,
)

// Improved Dark theme ColorScheme
private val DarkColorScheme = darkColorScheme(
    primary               = Teal200,
    onPrimary             = Color.Black,
    primaryContainer      = Teal200,
    onPrimaryContainer    = Color.Black,

    secondary             = Teal200,
    onSecondary           = Color.Black,
    secondaryContainer    = Teal200.copy(alpha = 0.2f),
    onSecondaryContainer  = Color.Black,

    // instead of pure black, use very dark gray
    background            = Color(0xFF121212),
    onBackground          = Color(0xFFE1E1E1),

    // a touch lighter than background for cards / sheets
    surface               = Color(0xFF1E1E1E),
    onSurface             = Color(0xFFE1E1E1),

    // slight contrast for elevated surfaces
    surfaceVariant        = Color(0xFF2A2A2A),
    onSurfaceVariant      = Color(0xFFCACACA),

    // subtle outlines
    outline               = Color(0xFF6F6F6F)
)

internal fun getLightColors() = LightColorScheme
internal fun getDarkColors()  = DarkColorScheme
