package ru.connect.data.network

import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.io.IOException
import org.koin.core.annotation.Singleton
import ru.connect.core.errors.HttpException
import ru.connect.core.errors.NoInternetException

@Singleton
class NetworkRequestExceptionsHandler {

    suspend fun handle(cause: Throwable) = when (cause) {
        is HttpRequestTimeoutException,
        is IOException,
            -> throw NoInternetException
        is ResponseException -> handle(cause.response.status.value, cause.response.bodyAsText(), cause)
        else -> throw cause
    }

    private fun handle(code: Int, errorResponse: String, cause: Throwable? = null) {
        when (code) {
            401 -> {
                throw HttpException.Unauthorized(errorResponse, cause)
            }
            in 400..499 -> {
                throw HttpException.Client(code, errorResponse, cause)
            }
            in 500..599 -> {
                val exception = HttpException.Server(code, errorResponse, cause)
                throw exception
            }
        }
    }
}
