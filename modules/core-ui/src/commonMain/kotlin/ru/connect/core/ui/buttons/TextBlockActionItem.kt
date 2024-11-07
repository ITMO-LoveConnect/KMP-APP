package ru.connect.core.ui.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.connect.core.ui.theme.ConnectTheme

@Composable
fun TextBlockActionItem(
    infoText: String,
    actionButton: String? = null,
    onAction: (() -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = CenterHorizontally,
    ) {
        Text(
            text = infoText,
            style = ConnectTheme.typography.material.subtitle1,
            textAlign = TextAlign.Center,
        )
        if (onAction != null && actionButton != null) {
            TextButton(onClick = onAction) {
                Text(
                    text = actionButton.uppercase(),
                    style = ConnectTheme.typography.material.button,
                )
            }
        }
    }
}
