package ru.connect.main.main.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource
import itmo_loveconnect.modules.features.main.generated.resources.Res
import itmo_loveconnect.modules.features.main.generated.resources.bottom_tab_first_title
import itmo_loveconnect.modules.features.main.generated.resources.bottom_tab_second_title

enum class BottomTabs(
    val title: StringResource,
    val imageVector: ImageVector,
) {
    FIRST_TAB(title = Res.string.bottom_tab_first_title, imageVector = Icons.Default.Add),
    SECOND_TAB(title = Res.string.bottom_tab_second_title, imageVector = Icons.Default.Home)
}
