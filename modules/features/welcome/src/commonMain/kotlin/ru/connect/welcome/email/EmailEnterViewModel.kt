package ru.connect.welcome.email

import org.koin.android.annotation.KoinViewModel
import ru.connect.core.extensions.launchCatching
import ru.connect.core.ui.UdfViewModel
import ru.connect.core.ui.error.UiError
import ru.connect.domain.auth.AuthRepository

@KoinViewModel(binds = [])
internal class EmailEnterViewModel(
    private val authRepository: AuthRepository,
) : UdfViewModel<EmailEnterUi, EmailEnterNavigationTarget>(EmailEnterUi()) {

    private var isuNumber = ""

    fun onIsuNumberEdit(isuNumber: String) {
        val isuNumberText = isuNumber.trim().also { this.isuNumber = it }
        _state.updateUi {
            copy(
                isuNumber = isuNumberText,
                isButtonEnabled = isuNumberText.trim().isNotEmpty(),
            )
        }
    }

    fun onSendButtonClick() {
        launchCatching(
            tryBlock = {
                _state.updateUi { copy(isButtonInProgress = true) }
                authRepository.sendIsuNumber(isuNumber)
                _state.navigateTo(EmailEnterNavigationTarget.OtpScreenTarget(isuNumber))
            }, catchBlock = { throwable ->
                throwable.printStackTrace()
                showAlertError(UiError.SnackBar.Default(message = "Извините, что-то пошло не так"))
            }, finalBlock = {
                _state.updateUi { copy(isButtonInProgress = false) }
            }
        )
    }

    fun onNavigateUp() {
        _state.navigateTo(EmailEnterNavigationTarget.Back)
    }
}
