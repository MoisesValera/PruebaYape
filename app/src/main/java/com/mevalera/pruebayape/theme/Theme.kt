package com.mevalera.pruebayape.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.mevalera.pruebayape.theme.Green200
import com.mevalera.pruebayape.theme.Teal200

private val DarkColorPalette = darkColors(
    primary = Green200,
    primaryVariant = Green200,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Green200,
    primaryVariant = Green200,
    secondary = Teal200
)

@Composable
fun TestTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}