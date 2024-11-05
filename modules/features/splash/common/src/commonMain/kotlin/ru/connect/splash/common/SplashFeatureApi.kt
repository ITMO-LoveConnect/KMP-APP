package ru.connect.splash.common

import kotlinx.serialization.Serializable
import ru.connect.core.navigation.FeatureApi

interface SplashFeatureApi : FeatureApi {

    companion object {
        @Serializable
        data object SplashFeatureTabDestination
    }
}
