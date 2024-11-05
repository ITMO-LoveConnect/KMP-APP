package ru.connect.core.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ExtendedColors(
    val surface: Surface = Surface(),
    val secondaryContainer: Color = Color.Unspecified,
) {

    val material: Colors
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colors

    @Immutable
    data class Surface(
        val surfaceContainerHighest: Color = Color.Unspecified,
        val surfaceContainerHigh: Color = Color.Unspecified,
        val surfaceContainer: Color = Color.Unspecified,
        val surfaceContainerLow: Color = Color.Unspecified,
        val surfaceContainerLowest: Color = Color.Unspecified,
    )

    internal companion object {

        val Light = ExtendedColors(
            surface = Surface(
                surfaceContainerHighest = surfaceContainerHighest,
                surfaceContainerHigh = surfaceContainerHigh,
                surfaceContainer = surfaceContainer,
                surfaceContainerLow = surfaceContainerLow,
                surfaceContainerLowest = surfaceContainerLowest,
            ),
            secondaryContainer = secondaryContainer,
        )

        internal fun provideExtended(): ExtendedColors = Light

        val LocalExtendedColors = staticCompositionLocalOf { ExtendedColors() }
    }
}

@Stable
fun Color.disabled(alpha: Float = ConnectContentAlpha.Light) = copy(alpha = alpha)
