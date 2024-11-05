package ru.connect.welcome.screen

sealed interface WelcomeNavigationTarget {
    data object LoginScreenDestination : WelcomeNavigationTarget
}
