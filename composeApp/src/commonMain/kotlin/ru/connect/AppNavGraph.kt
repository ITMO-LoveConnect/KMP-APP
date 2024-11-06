package ru.connect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import org.koin.compose.koinInject
import ru.connect.core.navigation.extension.register
import ru.connect.feature1.common.Feature1Api
import ru.connect.feature2.common.Feature2Api
import ru.connect.main.common.MainFeatureApi
import ru.connect.profile.common.ProfileFeatureApi
import ru.connect.splash.common.SplashFeatureApi
import ru.connect.welcome.common.WelcomeFeatureApi

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val mainFeature = koinInject<MainFeatureApi>()
    val feature1 = koinInject<Feature1Api>()
    val feature2 = koinInject<Feature2Api>()
    val splashFeatureApi = koinInject<SplashFeatureApi>()
    val welcomeFeatureApi = koinInject<WelcomeFeatureApi>()
    val profileFeatureApi = koinInject<ProfileFeatureApi>()
    val features = remember {
        listOf(
            mainFeature,
            feature1,
            feature2,
            splashFeatureApi,
            welcomeFeatureApi,
            profileFeatureApi,
        )
    }

    NavHost(
        navController = navController,
        startDestination = SplashFeatureApi.Companion.SplashFeatureTabDestination::class,
    ) {
        features.forEach { feature ->
            register(
                featureApi = feature,
                navController = navController,
                modifier = modifier
            )
        }
    }
}
