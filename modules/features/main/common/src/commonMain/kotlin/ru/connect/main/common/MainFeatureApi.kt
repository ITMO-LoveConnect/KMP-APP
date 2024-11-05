package ru.connect.main.common

import ru.connect.core.navigation.FeatureApi

interface MainFeatureApi : FeatureApi {

    companion object {
        const val MAIN_ROUTE: String = "MAIN_ROUTE"
    }
}
