package ru.connect.main.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import org.koin.compose.koinInject
import ru.connect.core.navigation.extension.register
import ru.connect.main.main.first.FirstTabNavigationApi
import ru.connect.main.main.third.ThirdTabNavigationApi

@Composable
fun BottomNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val firstTabFeature = koinInject<FirstTabNavigationApi>()
    val secondTabFeature = koinInject<ThirdTabNavigationApi>()
    NavHost(
        navController = navController,
        startDestination = FirstTabNavigationApi.Companion.FirstTabDestination::class,
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
