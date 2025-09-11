package com.insantech.personalitytest.ui.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = PsychePurple,
    secondary = TealMind,
    background = SoftWhite,
    surface = SoftWhite,
)

private val DarkColors = darkColorScheme(
    primary = PsychePurple,
    secondary = TealMind,
    background = IndigoNight,
    surface = IndigoNight,
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val scheme = if (isSystemInDarkTheme()) DarkColors else LightColors
    MaterialTheme(colorScheme = scheme, typography = Typography, content = content)
}