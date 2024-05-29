package com.simplelife.ss.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val MyColorScheme = lightColorScheme(
    primary = LightBlue,
    secondary = LightBlueVariant,
    background = Color.Black,
    surface = Color.Black,
    error = LightBlueAccent,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.White,
)

@Composable
fun SecondScreenTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = MyColorScheme,
        typography = MyTypography,
        content = content
    )
}
