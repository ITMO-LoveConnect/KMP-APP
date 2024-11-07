package ru.connect.main.main.first

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.core.annotation.Singleton
import ru.connect.core.navigation.FeatureApi

@Singleton(binds = [])
class LikeMathTabNavigationApi : FeatureApi {

    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController, modifier: Modifier) {
        navGraphBuilder.composable<LikeMathTabDestination> {
            LikeMathTabScreen()
        }
    }

    companion object {
        @Serializable
        data object LikeMathTabDestination
    }
}
