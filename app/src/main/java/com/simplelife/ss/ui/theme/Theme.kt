package com.simplelife.ss.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val MyColorScheme = lightColorScheme(

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
