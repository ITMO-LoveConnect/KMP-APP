package ru.connect.core.navigation

import androidx.navigation.PopUpToBuilder

data class PopupOptions(
    val popupToRoute: String,
    val options: PopUpToBuilder.() -> Unit = {},
)