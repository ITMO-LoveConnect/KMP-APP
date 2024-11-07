package ru.connect.data.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpCallValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Singleton
import ru.connect.domain.memory.TokenDataSource

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
        defaultRequest {
            host = "89.169.142.48"
            port = 8080
        }
    }

    @Singleton
    @Named("AUTHED")
    fun provideAuthedHttpClient(
        json: Json,
        exceptionsHandler: NetworkRequestExceptionsHandler,
        tokenDataSource: TokenDataSource,
    ) = HttpClient {
        expectSuccess = true
        install(ContentNegotiation) { json(json) }
        install(HttpCallValidator) {
            handleResponseException { throwable, _ -> exceptionsHandler.handle(throwable) }
        }
        headers {
            val token = tokenDataSource.getToken().orEmpty()
            append("Authorization", "Bearer $token")
        }
        install(HttpTimeout)
        defaultRequest {
            host = "89.169.142.48"
            port = 8080
        }
    }

    @Singleton
    fun provideJson() = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        isLenient = true
    }
}
