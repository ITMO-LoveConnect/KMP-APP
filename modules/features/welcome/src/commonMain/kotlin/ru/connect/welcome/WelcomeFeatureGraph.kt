package ru.connect.welcome

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.koin.core.annotation.Singleton
import ru.connect.welcome.common.WelcomeFeatureApi
import ru.connect.welcome.screen.WelcomeScreen

@Singleton
class WelcomeFeatureGraph : WelcomeFeatureApi {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController, modifier: Modifier) {
        navGraphBuilder.composable<WelcomeFeatureApi.Companion.WelcomeFeatureDestination> {
            WelcomeScreen()
        }
    }
}
