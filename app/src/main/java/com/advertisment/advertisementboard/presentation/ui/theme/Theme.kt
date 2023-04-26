package com.advertisment.advertisementboard.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.advertisment.advertismentboard.presentation.ui.theme.*

private val DarkColorPalette = darkColors(
    primary = Green,
    primaryVariant = Purple700,
    secondary = Teal200,
    surface = Black,
    onBackground = Color.White,
    onSurface = Color.White
)

private val LightColorPalette = lightColors(
    primary = Green,
    primaryVariant = Purple700,
    secondary = Teal200,
    surface = White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

@Composable
fun AdvertisementBoardTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
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