package ru.connect.core.navigation

import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import org.koin.core.annotation.Singleton

@Singleton
class FeatureNavigatorImpl(
    private val navController: NavHostController
) : FeatureNavigator {

    override fun navigate(destination: String, popupOptions: PopupOptions?, launchSingleTop: Boolean) {
        navController.navigate(destination,
            navOptions = navOptions {
                this.launchSingleTop = launchSingleTop
                popupOptions?.let { popUpTo(it.popupToRoute, it.options) }
            }
        )
    }

    override fun <T : Any> navigate(destination: T, popupOptions: PopupOptions?, launchSingleTop: Boolean) {
        navController.navigate(destination,
            navOptions = navOptions {
                this.launchSingleTop = launchSingleTop
                popupOptions?.let { popUpTo(it.popupToRoute, it.options) }
            }
        )
    }
}