package ru.connect.welcome

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import org.koin.core.annotation.Singleton
import ru.connect.welcome.common.WelcomeFeatureApi
import ru.connect.welcome.email.EmailEnterScreen
import ru.connect.welcome.email.EmailEnterScreenDestination
import ru.connect.welcome.screen.WelcomeFeatureScreenDestination
import ru.connect.welcome.screen.WelcomeScreen

@Singleton
class WelcomeFeatureGraph : WelcomeFeatureApi {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController, modifier: Modifier) {
        navGraphBuilder.navigation<WelcomeFeatureApi.Companion.WelcomeFeatureGraphDestination>(
            startDestination = WelcomeFeatureScreenDestination::class,
        ) {
            composable<WelcomeFeatureScreenDestination> {
                WelcomeScreen(
                    onLoginScreenNavigate = { navController.navigate(EmailEnterScreenDestination) },
                )
            }
            composable<EmailEnterScreenDestination> {
                EmailEnterScreen(
                    onNavigateUp = { navController.navigateUp() }
                )
            }
        }
    }
}
