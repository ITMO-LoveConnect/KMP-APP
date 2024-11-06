package ru.connect.data.auth

import kotlinx.coroutines.delay
import org.koin.core.annotation.Singleton
import ru.connect.domain.auth.AuthDataSource

@Singleton
class AuthApiDataSource : AuthDataSource {

    override suspend fun sendIsuNumber(isuNumber: String) {
        delay(500L)
//        TODO("Not yet implemented")
    }
}
