package ru.connect.core.ui

import androidx.compose.runtime.Immutable
import ru.connect.core.ui.error.AlertErrorHolder
import ru.connect.core.ui.error.AlertErrorHolderImpl
import ru.connect.core.ui.error.UiError

@Immutable
data class UiState<Ui, Nav>(
    val model: Ui,
    val navigationEvents: List<Nav> = emptyList(),
    override val alertErrors: List<UiError.SnackBar> = emptyList(),
) : AlertErrorHolder by AlertErrorHolderImpl(alertErrors)
