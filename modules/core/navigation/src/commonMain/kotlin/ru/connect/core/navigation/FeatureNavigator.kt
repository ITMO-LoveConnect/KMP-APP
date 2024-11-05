package ru.connect.core.navigation

interface FeatureNavigator {

    fun navigate(
        destination: String,
        popupOptions: PopupOptions? = null,
        launchSingleTop: Boolean = true,
    )

    fun <T : Any> navigate(
        destination: T,
        popupOptions: PopupOptions? = null,
        launchSingleTop: Boolean = true,
    )

}
