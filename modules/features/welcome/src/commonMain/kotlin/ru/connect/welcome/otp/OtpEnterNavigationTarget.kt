package ru.connect.welcome.otp

sealed interface OtpEnterNavigationTarget {
    data object Back : OtpEnterNavigationTarget
}
