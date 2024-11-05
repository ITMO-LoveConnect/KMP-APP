package ru.connect.core.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors

object ConnectColors {

    private val Dark = darkColors(
        primary = primaryDark,
        primaryVariant = inverseOnSurfaceLight, // Используем существующий цвет
        secondary = secondaryDark,
        secondaryVariant = secondaryContainerDark,
        background = backgroundDark,
        surface = surfaceDark,
        error = errorDark,
        onPrimary = onPrimaryDark,
        onSecondary = onSecondaryDark,
        onBackground = onBackgroundDark,
        onSurface = inverseOnSurfaceLight, // Используем существующий цвет
        onError = onErrorDark
    )

    private val Light = lightColors(
        primary = primaryLight,
        primaryVariant = inverseOnSurfaceLight, // Используем существующий цвет
        secondary = secondaryLight,
        secondaryVariant = secondaryContainerLight,
        background = backgroundLight,
        surface = surfaceLight,
        error = errorLight,
        onPrimary = onPrimaryLight,
        onSecondary = onSecondaryLight,
        onBackground = onBackgroundLight,
        onSurface = inverseOnSurfaceLight, // Используем существующий цвет
        onError = onErrorLight
    )

    internal fun provideMaterial(darkTheme: Boolean): Colors =
        if (darkTheme) Dark else Light
}
