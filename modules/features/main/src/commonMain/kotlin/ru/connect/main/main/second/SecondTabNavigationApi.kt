package ru.connect.main.main.second

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.core.annotation.Singleton
import ru.connect.core.navigation.FeatureApi

@Singleton
class SecondTabNavigationApi : FeatureApi {

    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController, modifier: Modifier) {
        navGraphBuilder.composable<SecondTabDestination> {
            SecondTabScreen()
        }
    }

    companion object {
        @Serializable
        data object SecondTabDestination
    }
}
