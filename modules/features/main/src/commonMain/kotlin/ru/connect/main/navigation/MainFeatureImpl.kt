package ru.connect.main.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import org.koin.core.annotation.Single
import ru.connect.main.common.MainGraphFeatureApi
import ru.connect.main.main.MainBottomScreen
import ru.connect.main.main.MainBottomScreenDestination

@Single
class MainFeatureImpl : MainGraphFeatureApi {

    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController, modifier: Modifier) {
        navGraphBuilder.navigation<MainGraphFeatureApi.Companion.MainGraphFeatureDestination>(
            startDestination = MainBottomScreenDestination
        ) {
            composable<MainBottomScreenDestination>() {
                MainBottomScreen()
            }
        }
    }
}
