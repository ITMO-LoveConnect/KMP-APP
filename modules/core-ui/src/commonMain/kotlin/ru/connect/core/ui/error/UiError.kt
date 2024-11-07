package ru.connect.core.ui.error

sealed class UiError {

    sealed class SnackBar(
        open val message: String,
        open val actionLabel: String? = null,
    ) : UiError() {
        data class Default(
            override val message: String = "",
            override val actionLabel: String? = null,
        ) : SnackBar(
            message = message,
            actionLabel = actionLabel,
        )
    }
}
