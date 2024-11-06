package ru.connect.core.ui.snackbar

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import ru.connect.core.ui.error.UiError

@Composable
fun SnackBarComponent(
    snackbarHostState: SnackbarHostState,
    error: UiError.SnackBar? = null,
    onDismiss: () -> Unit,
) {
    LaunchedEffect(error) {
        error?.let { error ->
            snackbarHostState.showSnackbar(
                message = error.message,
                actionLabel = error.actionLabel,
            )
        }
        onDismiss()
    }
}
