package ru.connect.splash.screen

import kotlinx.coroutines.delay
import org.koin.android.annotation.KoinViewModel
import ru.connect.core.extensions.launchCatching
import ru.connect.core.ui.UdfViewModel

@KoinViewModel(binds = [])
class SplashViewModel : UdfViewModel<Unit, SplashNavigationTarget>(Unit) {

    init {
        checkToken()
    }

    private fun checkToken() {
        launchCatching(
            tryBlock = {
                delay(DEBOUNCE_DELAY)
                _state.navigateTo(SplashNavigationTarget.WelcomeScreen)
            }, catchBlock = {
                _state.navigateTo(SplashNavigationTarget.WelcomeScreen)
            }
        )
    }

    companion object {
        private const val DEBOUNCE_DELAY = 500L
    }
}
