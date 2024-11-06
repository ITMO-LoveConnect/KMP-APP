package ru.connect.welcome.email

sealed interface EmailEnterNavigationTarget {
    data object Back : EmailEnterNavigationTarget
    class OtpScreenTarget(val isuNumber: String) : EmailEnterNavigationTarget
}
