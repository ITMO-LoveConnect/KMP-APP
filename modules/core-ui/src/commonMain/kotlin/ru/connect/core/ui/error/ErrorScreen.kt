package ru.connect.core.ui.error

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import ru.connect.core.ui.buttons.TextBlockActionItem

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    message: String? = null,
    refreshText: String? = null,
    onRefresh: (() -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(CenterVertically)
            .then(modifier)
    ) {
        TextBlockActionItem(
            infoText = message ?: "Что-то пошло не так",
            actionButton = refreshText ?: "Повторить",
            onAction = onRefresh,
        )
    }
}
