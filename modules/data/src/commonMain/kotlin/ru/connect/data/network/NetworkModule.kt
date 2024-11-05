package ru.connect.data.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpCallValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Module
import org.koin.core.annotation.Singleton

@Module
class NetworkModule {

    @Singleton
    fun provideBaseHttpClient(
        json: Json,
        exceptionsHandler: NetworkRequestExceptionsHandler,
    ) = HttpClient {
        expectSuccess = true
        install(ContentNegotiation) { json(json) }
        install(HttpCallValidator) {
            handleResponseException { throwable, _ -> exceptionsHandler.handle(throwable) }
        }
        install(HttpTimeout)
    }

    @Singleton
    fun provideJson() = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        isLenient = true
    }
}
