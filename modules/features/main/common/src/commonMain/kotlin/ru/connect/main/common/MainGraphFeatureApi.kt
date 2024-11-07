package ru.connect.main.common

import kotlinx.serialization.Serializable
import ru.connect.core.navigation.FeatureApi

interface MainGraphFeatureApi : FeatureApi {

    companion object {

        @Serializable
        data object MainGraphFeatureDestination
    }
}
