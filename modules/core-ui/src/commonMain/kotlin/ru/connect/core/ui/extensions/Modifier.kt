package ru.connect.core.ui.extensions

import androidx.compose.foundation.layout.widthIn
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun Modifier.maxFullScreenWidth() = widthIn(max = 400.dp)
