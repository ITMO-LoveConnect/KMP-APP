package ru.connect.data.memory

import com.russhwolf.settings.Settings
import com.russhwolf.settings.contains
import com.russhwolf.settings.get
import org.koin.core.annotation.Singleton
import ru.connect.domain.memory.LocalMemoryDataSource

@Singleton
class LocalMemoryDataSourceImpl(
    private val settings: Settings
) : LocalMemoryDataSource {

    override suspend fun saveSession(userId: String, token: String) {
        settings.putString(USER_ID, userId)
        settings.putString(TOKEN, token)
    }

    override suspend fun clearSession() {
        settings.remove(USER_ID)
        settings.remove(TOKEN)
    }

    override suspend fun getPinKey(): String = settings[TOKEN]!!
    override suspend fun getUserId(): String = settings[USER_ID]!!

    override suspend fun isContainsAuthData(): Boolean = settings.contains(USER_ID) && settings.contains(TOKEN)

    private companion object {
        const val USER_ID = "USER_ID"
        const val TOKEN = "TOKEN"
    }
}
