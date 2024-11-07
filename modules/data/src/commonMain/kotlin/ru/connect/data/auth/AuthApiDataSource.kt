package ru.connect.data.auth

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable
import org.koin.core.annotation.Singleton
import ru.connect.domain.auth.AuthDataSource
import ru.connect.domain.auth.models.RegisterModel
import ru.connect.domain.auth.models.SessionToken
import ru.connect.domain.auth.models.UserIsuProfile

@Singleton
class AuthApiDataSource(
    private val client: HttpClient,
) : AuthDataSource {

    override suspend fun sendIsuNumber(isuNumber: String) {
        client.post(
            urlString = "auth/sendConfirmationCode"
        ) {
            contentType(ContentType.Application.Json)
            setBody(IsuNumberDto(isuNumber))
        }
    }

    override suspend fun login(isuNumber: String, otpCode: String): SessionToken {
        return client.post(
            urlString = "auth/login"
        ) {
            contentType(ContentType.Application.Json)
            setBody(LoginDto(isuNumber, otpCode.toLong()))
        }.body()
    }

    override suspend fun requestProfilledProfile(isuNumber: String, otpCode: String): UserIsuProfile {
        return client.post(
            urlString = "auth/requestPrefilledProfile"
        ) {
            contentType(ContentType.Application.Json)
            setBody(LoginDto(isuNumber, otpCode.toLong()))
        }.body()
    }

    override suspend fun register(request: RegisterModel): SessionToken {
        return client.post(
            urlString = "auth/register"
        ) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    override suspend fun isRegistered(isuNumber: String): Boolean {
        return client.get(
            urlString = "auth/isRegistered?isuNumber=$isuNumber"
        ).body<IsRegisteredDto>().registered
    }

    @Serializable
    private data class IsuNumberDto(val isuNumber: String)

    @Serializable
    private data class LoginDto(val isuNumber: String, val confirmationCode: Long)

    @Serializable
    private data class IsRegisteredDto(val registered: Boolean)
}
