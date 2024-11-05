package ru.connect

import ru.connect.feature1.common.Feature1Api
import ru.connect.feature2.common.Feature2Api
import ru.connect.main.common.MainFeatureApi

/**
 * WARNING!!! Don't use it in real project! Use real DI libraries: Dagger, Hilt, Koin..
 * We did this to simplify the example
 */
object DependencyProvider {

    /* Don't use lateinit in real project :) */
    private lateinit var mainFeatureApi: MainFeatureApi
    private lateinit var feature1Api: Feature1Api
    private lateinit var feature2Api: Feature2Api

    fun provideImpl(
        mainFeatureApi: MainFeatureApi,
        feature1Api: Feature1Api,
        feature2Api: Feature2Api
    ) {
        this.mainFeatureApi = mainFeatureApi
        this.feature1Api = feature1Api
        this.feature2Api = feature2Api
    }

    fun mainFeature(): MainFeatureApi = mainFeatureApi

    fun feature1(): Feature1Api = feature1Api

    fun feature2(): Feature2Api = feature2Api
}
