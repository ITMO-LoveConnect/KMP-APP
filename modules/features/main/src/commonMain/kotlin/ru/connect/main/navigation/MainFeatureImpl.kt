package ru.connect.main.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.koin.core.annotation.Single
import ru.connect.main.common.MainFeatureApi
import ru.connect.main.common.MainFeatureApi.Companion.MAIN_ROUTE
import ru.connect.main.main.MainBottomScreen

@Single
class MainFeatureImpl : MainFeatureApi {

    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController, modifier: Modifier) {
        navGraphBuilder.composable(MAIN_ROUTE) {
            MainBottomScreen()
        }
    }
}
