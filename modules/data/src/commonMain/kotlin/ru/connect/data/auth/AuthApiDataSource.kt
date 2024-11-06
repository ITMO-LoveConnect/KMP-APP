package ru.connect.data.auth

import kotlinx.coroutines.delay
import org.koin.core.annotation.Singleton
import ru.connect.domain.auth.AuthDataSource
import ru.connect.domain.auth.models.Session

@Singleton
class AuthApiDataSource : AuthDataSource {

    override suspend fun sendIsuNumber(isuNumber: String) {
        delay(500L)
    }

    override suspend fun sendOtpCode(isuNumber: String, otpCode: String): Session {
        delay(500L)

        return Session(token = "token")
    }
}
