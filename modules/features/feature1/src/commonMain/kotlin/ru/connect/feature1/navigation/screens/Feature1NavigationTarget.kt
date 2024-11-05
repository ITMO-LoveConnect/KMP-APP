package ru.connect.feature1.navigation.screens

sealed interface Feature1NavigationTarget {

    data object Back : Feature1NavigationTarget
}
