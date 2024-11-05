package ru.connect.welcome.screen

import org.koin.android.annotation.KoinViewModel
import ru.connect.core.ui.UdfViewModel

@KoinViewModel(binds = [])
class WelcomeViewModel : UdfViewModel<Unit, WelcomeNavigationTarget>(Unit) {

    fun onLoginButtonClick() {
        _state.navigateTo(WelcomeNavigationTarget.LoginScreenDestination)
    }
}
