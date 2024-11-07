package ru.connect.domain.auth

import org.koin.core.annotation.Provided
import org.koin.core.annotation.Singleton
import ru.connect.domain.auth.models.Session
import ru.connect.domain.memory.LocalMemoryDataSource

@Singleton
class AuthRepository(
    @Provided private val dataSource: AuthDataSource,
    @Provided private val memoryDataSource: LocalMemoryDataSource,
) {

    suspend fun sendIsuNumber(isuNumber: String) {
        dataSource.sendIsuNumber(isuNumber)
    }

    suspend fun sendOtpCode(isuNumber: String, otpCode: String): Session =
        dataSource.sendOtpCode(isuNumber, otpCode)

    suspend fun isContainsAuthData(): Boolean = memoryDataSource.isContainsAuthData()

    suspend fun saveSession(userId: String, token: String) = memoryDataSource.saveSession(userId, token)
}
