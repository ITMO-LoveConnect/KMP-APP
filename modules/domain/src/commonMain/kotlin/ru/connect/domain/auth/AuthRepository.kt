package ru.connect.domain.auth

import org.koin.core.annotation.Provided
import org.koin.core.annotation.Singleton
import ru.connect.domain.auth.models.Session

@Singleton
class AuthRepository(
    @Provided private val dataSource: AuthDataSource,
) {

    suspend fun sendIsuNumber(isuNumber: String) {
        dataSource.sendIsuNumber(isuNumber)
    }

    suspend fun sendOtpCode(isuNumber: String, otpCode: String): Session =
        dataSource.sendOtpCode(isuNumber, otpCode)
}
