package ru.connect.core.navigation

interface FeatureNavigator {

    fun navigate(
        destination: String,
        popupOptions: PopupOptions<String>? = null,
        launchSingleTop: Boolean = true,
    )

    fun <T : Any> navigate(
        destination: T,
        popupOptions: PopupOptions<T>? = null,
        launchSingleTop: Boolean = true,
    )

}
