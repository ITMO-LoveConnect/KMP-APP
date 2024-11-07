package ru.connect.splash

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.koin.core.annotation.Singleton
import ru.connect.splash.common.SplashFeatureApi
import ru.connect.splash.screen.SplashScreen

@Singleton
class SplashFeatureGraph : SplashFeatureApi {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController, modifier: Modifier) {
        navGraphBuilder.composable<SplashFeatureApi.Companion.SplashFeatureTabDestination> {
            SplashScreen()
        }
    }
}
