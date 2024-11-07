package ru.connect.main.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import org.koin.compose.koinInject
import ru.connect.core.navigation.extension.register
import ru.connect.main.main.first.LikeMathTabNavigationApi
import ru.connect.main.main.second.SecondTabNavigationApi

@Composable
fun BottomNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val firstTabFeature = koinInject<LikeMathTabNavigationApi>()
    val secondTabFeature = koinInject<SecondTabNavigationApi>()
    NavHost(
        navController = navController,
        startDestination = LikeMathTabNavigationApi.Companion.LikeMathTabDestination::class,
    ) {
        register(
            featureApi = firstTabFeature,
            navController = navController,
            modifier = modifier
        )

        register(
            featureApi = secondTabFeature,
            navController = navController,
            modifier = modifier
        )
    }
}
