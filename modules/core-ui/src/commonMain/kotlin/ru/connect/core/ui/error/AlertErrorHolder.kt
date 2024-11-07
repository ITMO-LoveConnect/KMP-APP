package ru.connect.core.ui.error

interface AlertErrorHolder {
    val alertErrors: List<UiError.SnackBar>
    val alertError: UiError.SnackBar?
    fun List<UiError.SnackBar>.consumeAlert(): List<UiError.SnackBar>
}
