package ru.connect.welcome.common

import kotlinx.serialization.Serializable
import ru.connect.core.navigation.FeatureApi

interface WelcomeFeatureApi : FeatureApi {

    companion object {
        @Serializable
        data object WelcomeFeatureGraphDestination
    }
}
