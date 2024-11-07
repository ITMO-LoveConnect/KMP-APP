package ru.connect.domain.auth

import org.koin.core.annotation.Provided
import org.koin.core.annotation.Singleton
import ru.connect.domain.auth.models.RegisterModel
import ru.connect.domain.auth.models.SessionToken
import ru.connect.domain.auth.models.UserIsuProfile
import ru.connect.domain.memory.LocalMemoryDataSource
import ru.connect.domain.memory.TokenDataSource

@Singleton
class AuthRepository(
    @Provided private val dataSource: AuthDataSource,
    @Provided private val memoryDataSource: LocalMemoryDataSource,
    private val tokenDataSource: TokenDataSource,
) {

    suspend fun sendIsuNumber(isuNumber: String) {
        dataSource.sendIsuNumber(isuNumber)
    }

    suspend fun isRegistered(isuNumber: String): Boolean = dataSource.isRegistered(isuNumber)

    suspend fun login(isuNumber: String, otpCode: String): SessionToken =
        dataSource.login(isuNumber, otpCode)

    suspend fun requestProfilledProfile(isuNumber: String, otpCode: String): UserIsuProfile =
        dataSource.requestProfilledProfile(isuNumber, otpCode)

    suspend fun isContainsAuthData(): Boolean = memoryDataSource.isContainsAuthData().also {
        if (it) tokenDataSource.setToken(memoryDataSource.getToken())
    }

    suspend fun register(request: RegisterModel) {
        val session = dataSource.register(request)
        memoryDataSource.saveSession(request.isuNumber, session.token)
        tokenDataSource.setToken(session.token)
    }

    suspend fun saveSession(userId: String, token: String) = memoryDataSource.saveSession(userId, token).also {
        tokenDataSource.setToken(token)
    }
}
