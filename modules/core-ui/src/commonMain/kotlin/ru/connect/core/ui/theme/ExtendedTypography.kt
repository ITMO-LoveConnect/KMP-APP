package ru.connect.core.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

@Immutable
data class ExtendedTypography(
    val material: Typography = Typography(),
) {

    internal companion object {
        val LocalExtendedTypography = staticCompositionLocalOf { ExtendedTypography() }
    }
}
