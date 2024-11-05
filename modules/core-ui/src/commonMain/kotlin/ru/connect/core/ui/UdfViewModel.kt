package ru.connect.core.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.asStateFlow
import kotlin.coroutines.CoroutineContext

/**
 * UdfViewModel is a base class for ViewModels.
 * It provides the CoroutineScope with Dispatchers.Default by default
 * to perform operations with the state on the background thread.
 */
@Suppress("PropertyName", "VariableNaming")
open class UdfViewModel<Ui, Nav>(
    initialState: Ui
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Default

    protected val _state = MutableScreenUiStateFlow<Ui, Nav>(initialState)
    val state = _state.asStateFlow()

    fun handleNavigation(navigate: (Nav) -> Unit) = _state.handleNavigation(navigate)

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }
}
