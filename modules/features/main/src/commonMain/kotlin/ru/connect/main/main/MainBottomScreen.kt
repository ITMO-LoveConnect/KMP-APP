package ru.connect.main.main

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.koin.compose.viewmodel.koinViewModel
import ru.connect.core.ui.theme.ConnectTheme
import ru.connect.main.main.first.FirstTabNavigationApi
import ru.connect.main.main.models.BottomTabs
import ru.connect.main.main.navigation.BottomNavGraph
import ru.connect.main.main.second.SecondTabNavigationApi
import ru.connect.main.main.third.ThirdTabNavigationApi

@Composable
internal fun MainBottomScreen(
    viewModel: MainBottomViewModel = koinViewModel(),
) {
    val navController = rememberNavController()
    val uiState = viewModel.state.collectAsState().value
    val ui = uiState.model

    viewModel.handleNavigation { target ->
        when (target) {
            MainBottomNavigationTarget.FirstTabTarget -> navController.navigate(
                FirstTabNavigationApi.Companion.FirstTabDestination,
                navOptions = navOptions {
                    popUpTo(navController.graph.startDestinationRoute.orEmpty()) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            )
            MainBottomNavigationTarget.SecondTabTarget -> navController.navigate(
                SecondTabNavigationApi.Companion.SecondTabDestination,
                navOptions = navOptions {
                    popUpTo(navController.graph.startDestinationRoute.orEmpty()) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            )
            MainBottomNavigationTarget.ThirdTabTarget -> navController.navigate(
                ThirdTabNavigationApi.Companion.ThirdTabScreenDestination,
                navOptions = navOptions {
                    popUpTo(navController.graph.startDestinationRoute.orEmpty()) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            )
        }
    }

    Scaffold(
        bottomBar = {
            BottomBar(
                ui = ui,
                onTabClick = viewModel::onTabClick,
            )
        }
    ) { innerPaddingModifier ->
        BottomNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPaddingModifier)
        )
    }
}

@Composable
private fun BottomBar(
    ui: MainBottomUi,
    onTabClick: (BottomTabs) -> Unit,
) {
    NavigationBar(
        containerColor = ConnectTheme.colors.surface.surfaceContainer,
        windowInsets = WindowInsets.captionBar,
    ) {
        ui.tabs.forEach { tab ->
            NavigationBarItem(
                modifier = Modifier.navigationBarsPadding(),
                icon = { Icon(imageVector = tab.imageVector, contentDescription = "") },
                selected = tab == ui.selectedTab,
                onClick = { onTabClick(tab) },
                colors = NavigationBarItemDefaults.colors().copy(
                    selectedIndicatorColor = ConnectTheme.colors.secondaryContainer,
                )
            )
        }
    }
}
