package ru.connect.core.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class MutableScreenUiStateFlow<Ui, Nav>(
    model: Ui,
    navigationEvents: List<Nav> = emptyList(),
) : MutableStateFlow<UiState<Ui, Nav>> by MutableStateFlow(
    UiState(
        model = model,
        navigationEvents = navigationEvents,
    )
) {

    val lock = Mutex()
    private val scope = CoroutineScope(Dispatchers.Default)

    suspend fun getUi() = lock.withLock { value.model }

    suspend inline fun update(action: UiState<Ui, Nav>.() -> UiState<Ui, Nav>) = lock.withLock {
        value = value.action()
    }

    fun navigateTo(target: Nav) = scope.launch {
        lock.withLock {
            value = value.copy(
                navigationEvents = value.navigationEvents + target
            )
        }
    }

    fun updateUi(action: Ui.() -> Ui) = scope.launch {
        lock.withLock {
            value = value.copy(model = value.model.action())
        }
    }

    fun handleNavigation(navigate: (Nav) -> Unit) = CoroutineScope(Dispatchers.Main).launch {
        if (value.navigationEvents.isNotEmpty()) {
            navigate(value.navigationEvents.first())
            update { copy(navigationEvents = navigationEvents.drop(1)) }
        }
    }
}
