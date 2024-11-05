package ru.connect

import androidx.compose.ui.window.ComposeUIViewController

@Suppress("FunctionNaming")
fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }
