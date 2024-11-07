package ru.connect.welcome.otp

sealed interface OtpEnterNavigationTarget {
    data object Back : OtpEnterNavigationTarget
    data object CreateProfile : OtpEnterNavigationTarget
    data object MainScreen : OtpEnterNavigationTarget
}
