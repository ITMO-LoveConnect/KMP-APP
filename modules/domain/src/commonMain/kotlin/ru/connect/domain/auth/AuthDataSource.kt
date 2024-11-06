package ru.connect.domain.auth

interface AuthDataSource {

    suspend fun sendIsuNumber(isuNumber: String)
}
