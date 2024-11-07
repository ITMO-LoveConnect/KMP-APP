package ru.connect.domain.memory

interface LocalMemoryDataSource {

    suspend fun saveSession(userId: String, token: String)

    suspend fun clearSession()

    suspend fun isContainsAuthData(): Boolean

    suspend fun getToken(): String
    suspend fun getUserId(): String
}
