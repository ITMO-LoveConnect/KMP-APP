package ru.connect.main.main

import androidx.compose.runtime.Immutable
import ru.connect.main.main.models.BottomTabs

@Immutable
data class MainBottomUi(
    val tabs: List<BottomTabs>,
    val selectedTab: BottomTabs = tabs.first(),
)
