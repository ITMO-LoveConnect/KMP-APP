package ru.connect.profile.common

import kotlinx.serialization.Serializable
import ru.connect.core.navigation.FeatureApi

interface ProfileFeatureApi : FeatureApi {

    companion object {
        @Serializable
        data object ProfileFeatureGraphDestination
    }
}
