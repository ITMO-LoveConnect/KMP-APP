package ru.connect.welcome.email

import androidx.compose.runtime.Immutable

@Immutable
data class EmailEnterUi(
    val isuNumber: String = "",
    val isButtonEnabled: Boolean = false,
    val isButtonInProgress: Boolean = false,
)
