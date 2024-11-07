package ru.connect.core.ui.buttons

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.connect.core.ui.theme.ConnectShapes
import ru.connect.core.ui.theme.ConnectTheme
import ru.connect.core.ui.theme.disabled

@Composable
fun ProgressButton(
    text: String,
    modifier: Modifier = Modifier,
    inProgress: Boolean = false,
    enabled: Boolean = true,
    colors: ButtonColors = ProgressButtonDefaults.buttonColors(),
    progressColor: Color = ConnectTheme.colors.material.onPrimary,
    elevation: ButtonElevation? = ProgressButtonDefaults.elevation(),
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = !inProgress && enabled,
        shape = ConnectShapes.RoundedCornerShape6,
        modifier = modifier.height(48.dp),
        colors = colors,
        elevation = elevation
    ) {
        if (inProgress) {
            CircularProgressIndicator(
                color = progressColor,
                modifier = Modifier.size(24.dp),
                strokeWidth = 3.dp,
            )
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = text.uppercase())
            }
        }
    }
}

object ProgressButtonDefaults {

    @Composable
    fun buttonColors(
        backgroundColor: Color = ConnectTheme.colors.material.primary,
        contentColor: Color = contentColorFor(backgroundColor),
        disabledBackgroundColor: Color = ConnectTheme.colors.material.primary.disabled(),
        disabledContentColor: Color = ConnectTheme.colors.material.onPrimary,
    ): ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        disabledBackgroundColor = disabledBackgroundColor,
        disabledContentColor = disabledContentColor,
    )

    @Composable
    fun elevation(
        defaultElevation: Dp = 14.dp,
        pressedElevation: Dp = 0.dp,
        disabledElevation: Dp = 0.dp,
        hoveredElevation: Dp = 6.dp,
        focusedElevation: Dp = 6.dp,
    ): ButtonElevation = ButtonDefaults.elevation(
        defaultElevation = defaultElevation,
        pressedElevation = pressedElevation,
        disabledElevation = disabledElevation,
        hoveredElevation = hoveredElevation,
        focusedElevation = focusedElevation,
    )
}
