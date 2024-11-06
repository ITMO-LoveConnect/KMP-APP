package ru.connect.welcome.otp

import androidx.compose.runtime.Immutable

@Immutable
data class OtpEnterUi(
    val otpCode: String = "",
    val email: String,
    val isButtonEnabled: Boolean = false,
    val isButtonInProgress: Boolean = false,
)
