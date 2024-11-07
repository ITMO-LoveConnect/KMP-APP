package ru.connect.profile

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import org.koin.core.annotation.Singleton
import ru.connect.profile.common.ProfileFeatureApi
import ru.connect.profile.create.ProfileCreateScreen
import ru.connect.profile.create.ProfileCreateScreenDestination

@Singleton
class ProfileFeatureGraph : ProfileFeatureApi {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController, modifier: Modifier) {
        navGraphBuilder.navigation<ProfileFeatureApi.Companion.ProfileFeatureGraphDestination>(
            startDestination = ProfileCreateScreenDestination::class,
        ) {
            composable<ProfileCreateScreenDestination> {
                ProfileCreateScreen(
                    onNavigateUp = { navController.navigateUp() }
                )
            }
        }
    }
}
