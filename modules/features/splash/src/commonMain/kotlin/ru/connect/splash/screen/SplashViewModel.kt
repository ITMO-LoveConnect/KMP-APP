package ru.connect.splash.screen

import kotlinx.coroutines.delay
import org.koin.android.annotation.KoinViewModel
import ru.connect.core.extensions.launchCatching
import ru.connect.core.ui.UdfViewModel
import ru.connect.domain.auth.AuthInteractor

@KoinViewModel(binds = [])
class SplashViewModel(
    private val authInteractor: AuthInteractor,
) : UdfViewModel<Unit, SplashNavigationTarget>(Unit) {

    init {
        checkToken()
    }

    private fun checkToken() {
        launchCatching(
            tryBlock = {
                delay(DEBOUNCE_DELAY)
                if (authInteractor.isContainsAuthData()) {
                    _state.navigateTo(SplashNavigationTarget.MainScreen)
                } else {
                    _state.navigateTo(SplashNavigationTarget.WelcomeScreen)
                }
            }, catchBlock = {
                _state.navigateTo(SplashNavigationTarget.WelcomeScreen)
            }
        )
    }

    companion object {
        private const val DEBOUNCE_DELAY = 500L
    }
}
