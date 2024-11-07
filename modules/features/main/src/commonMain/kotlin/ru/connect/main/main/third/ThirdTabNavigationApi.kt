package ru.connect.main.main.third

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.core.annotation.Singleton
import ru.connect.core.navigation.FeatureApi

@Singleton
class ThirdTabNavigationApi : FeatureApi {

    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController, modifier: Modifier) {
        navGraphBuilder.composable<ThirdTabScreenDestination> {
            ThirdTabScreen()
        }
    }

    companion object {
        @Serializable
        data object ThirdTabScreenDestination
    }
}
