package ru.connect.welcome.otp

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import org.koin.android.annotation.KoinViewModel
import ru.connect.core.extensions.launchCatching
import ru.connect.core.ui.UdfViewModel
import ru.connect.core.ui.error.UiError
import ru.connect.domain.auth.AuthRepository

@KoinViewModel
internal class OtpEnterViewModel(
    savedStateHandle: SavedStateHandle,
    private val authRepository: AuthRepository,
) : UdfViewModel<OtpEnterUi, OtpEnterNavigationTarget>(
    OtpEnterUi(
        email = with(getArgs(savedStateHandle).isuNumber) {
            "$this@niuitmo.ru"
        }
    )
) {

    private val isuNumber: String = getArgs(savedStateHandle).isuNumber

    private var otpCode = ""

    fun onIsuNumberEdit(otpCode: String) {
        val isuNumberText = otpCode.trim().filter { it.isDigit() }.also { this.otpCode = it }
        _state.updateUi {
            copy(
                otpCode = isuNumberText,
                isButtonEnabled = isuNumberText.trim().isNotEmpty(),
            )
        }
    }

    fun onSendButtonClick() {
        launchCatching(
            tryBlock = {
                _state.updateUi { copy(isButtonInProgress = true) }
                authRepository.sendOtpCode(isuNumber, otpCode)
            }, catchBlock = { throwable ->
                showAlertError(UiError.SnackBar.Default(message = "Извините, что-то пошло не так"))
            }, finalBlock = {
                _state.updateUi { copy(isButtonInProgress = false) }
            }
        )
    }

    fun onNavigateUp() {
        _state.navigateTo(OtpEnterNavigationTarget.Back)
    }

    companion object {
        private fun getArgs(savedStateHandle: SavedStateHandle) = savedStateHandle.toRoute<OtpEnterScreenDestination>()
    }
}
