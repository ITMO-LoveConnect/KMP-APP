package ru.connect.feature1.navigation.screens

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import org.koin.android.annotation.KoinViewModel
import ru.connect.core.cache.CachePolicy
import ru.connect.core.extensions.launchCatching
import ru.connect.core.state.LoadingState
import ru.connect.core.state.error
import ru.connect.core.state.idle
import ru.connect.core.state.loading
import ru.connect.core.state.success
import ru.connect.core.ui.UdfViewModel
import ru.connect.domain.feature1.Feature1Repository

@KoinViewModel(binds = [])
internal class Feature1ViewModel(
    private val repository: Feature1Repository,
) : UdfViewModel<LoadingState<Feature1Ui, Unit>, Feature1NavigationTarget>(idle()) {

    init {
        loadScreen()
    }

    fun onNavigateUp() {
        _state.navigateTo(Feature1NavigationTarget.Back)
    }

    fun onRefreshButtonClick() {
        loadScreen()
    }

    private fun loadScreen() {
        viewModelScope.launchCatching(
            tryBlock = {
                _state.updateUi { loading() }
                delay(1_000L)

                val result = requireNotNull(repository.fetch(Unit, cachePolicy = CachePolicy.REFRESH))
                _state.updateUi { Feature1Ui(resultText = result.fact).success() }
            }, catchBlock = {
                _state.updateUi { Unit.error() }
            }
        )
    }
}
