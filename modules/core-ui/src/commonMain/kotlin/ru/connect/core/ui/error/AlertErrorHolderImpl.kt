package ru.connect.core.ui.error

class AlertErrorHolderImpl(override val alertErrors: List<UiError.SnackBar>) : AlertErrorHolder {

    override val alertError: UiError.SnackBar? get() = alertErrors.firstOrNull()

    override fun List<UiError.SnackBar>.consumeAlert(): List<UiError.SnackBar> = this.drop(1)
}
