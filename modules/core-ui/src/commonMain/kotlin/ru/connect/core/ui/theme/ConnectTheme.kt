package ru.connect.core.ui.theme

import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import ru.connect.core.ui.theme.ExtendedColors.Companion.LocalExtendedColors

@Composable
fun ConnectTheme(
    darkTheme: Boolean = false,
//    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = ConnectColors.provideMaterial(darkTheme),
    ) {
        val extendedColors = remember(darkTheme) {
            ExtendedColors.provideExtended()
        }
        CompositionLocalProvider(
            // colors providing
            LocalExtendedColors provides extendedColors,
            // content color (text on background)
            LocalContentColor provides extendedColors.material.onBackground,
            content = content
        )
    }
}

object ConnectTheme {

    val colors: ExtendedColors
        @Composable
        @ReadOnlyComposable
        get() = LocalExtendedColors.current
}
