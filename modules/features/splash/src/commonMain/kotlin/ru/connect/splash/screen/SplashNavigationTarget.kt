package ru.connect.splash.screen

sealed interface SplashNavigationTarget {
    data object WelcomeScreen : SplashNavigationTarget
    data object MainScreen : SplashNavigationTarget
}
