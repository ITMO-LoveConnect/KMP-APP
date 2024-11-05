package ru.connect.main.main

import androidx.lifecycle.viewModelScope
import org.koin.android.annotation.KoinViewModel
import ru.connect.core.extensions.launchCatching
import ru.connect.core.ui.UdfViewModel
import ru.connect.main.main.models.BottomTabs

@KoinViewModel(binds = [])
internal class MainBottomViewModel : UdfViewModel<MainBottomUi, MainBottomNavigationTarget>(
    MainBottomUi(
        tabs = BottomTabs.entries,
    )
) {
    fun onTabClick(bottomTab: BottomTabs) {
        viewModelScope.launchCatching {
            _state.updateUi { copy(selectedTab = bottomTab) }

            when (bottomTab) {
                BottomTabs.FIRST_TAB -> MainBottomNavigationTarget.FirstTabTarget
                BottomTabs.SECOND_TAB -> MainBottomNavigationTarget.SecondTabTarget
            }.also(_state::navigateTo)
        }
    }
}
