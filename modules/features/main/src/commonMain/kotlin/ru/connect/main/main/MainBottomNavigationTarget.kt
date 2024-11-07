package ru.connect.main.main

sealed interface MainBottomNavigationTarget {
    data object FirstTabTarget : MainBottomNavigationTarget
    data object SecondTabTarget : MainBottomNavigationTarget
    data object ThirdTabTarget : MainBottomNavigationTarget
}
