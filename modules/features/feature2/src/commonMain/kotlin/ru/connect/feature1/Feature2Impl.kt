package ru.connect.feature1

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.koin.core.annotation.Single
import ru.connect.feature2.common.Feature2Api

@Single
class Feature2Impl : Feature2Api {

    override val feature2Route: String = "feature2Route"

    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController, modifier: Modifier) {
        navGraphBuilder.composable(feature2Route) {}
    }
}
