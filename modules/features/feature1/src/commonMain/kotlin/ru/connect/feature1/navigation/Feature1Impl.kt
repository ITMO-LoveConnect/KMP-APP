package ru.connect.feature1.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.koin.core.annotation.Single
import ru.connect.feature1.common.Feature1Api
import ru.connect.feature1.navigation.screens.Feature1Screen

@Single
class Feature1Impl : Feature1Api() {

    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController, modifier: Modifier) {
        navGraphBuilder.composable(feature1Route) {
            Feature1Screen(
                onNavigateUp = navController::navigateUp,
            )
        }
    }
}
