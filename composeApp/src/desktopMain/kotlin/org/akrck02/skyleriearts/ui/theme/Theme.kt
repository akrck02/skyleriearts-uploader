package org.akrck02.skyleriearts.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun getSystemThemeColors(): Colors {
    return lightColors(
        primary = Color(0xFF9A8E75),
        onPrimary = Color(0xFFFFFFFF),

        secondary = Color(0xFF958DA5),
        onSecondary = Color(0xFF9A8E75),

        surface = Color(0xFFDCD8D0),
        onSurface = Color(0xFF9A8E75),

        background = Color(0xFFEDECEA),
        onBackground = Color(0xFF9A8E75),

        error = Color.Red,
        onError = Color.White,
    )
}