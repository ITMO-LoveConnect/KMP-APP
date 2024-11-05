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

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val mainFeature = koinInject<MainFeatureApi>()
    val feature1 = koinInject<Feature1Api>()
    val feature2 = koinInject<Feature2Api>()
    val features = remember {
        listOf(
            mainFeature,
            feature1,
            feature2,
        )
    }

    NavHost(
        navController = navController,
        startDestination = MainFeatureApi.MAIN_ROUTE,
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
