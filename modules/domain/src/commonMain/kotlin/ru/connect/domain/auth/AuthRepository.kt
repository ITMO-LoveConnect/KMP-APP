package ru.connect.domain.auth

import org.koin.core.annotation.Provided
import org.koin.core.annotation.Singleton

@Singleton
class AuthRepository(
    @Provided private val dataSource: AuthDataSource,
) {

    suspend fun sendIsuNumber(isuNumber: String) {
        dataSource.sendIsuNumber(isuNumber)
    }
}
