package ru.connect.core.navigation

import androidx.navigation.PopUpToBuilder

data class PopupOptions<T>(
    val popupToRoute: T,
    val options: PopUpToBuilder.() -> Unit = {},
)