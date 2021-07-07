package com.password.saver.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
        primary = ColorPrimary,
        primaryVariant = ColorPrimaryDark,
        secondary = Teal200
)

@Composable
fun PasswordSaverTheme(content: @Composable () -> Unit) {
    MaterialTheme(
            colors = DarkColorPalette,
            typography = Typography,
            shapes = Shapes,
            content = content
    )
}