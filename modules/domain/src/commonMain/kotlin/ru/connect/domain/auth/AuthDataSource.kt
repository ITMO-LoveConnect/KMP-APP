package ru.connect.domain.auth

import ru.connect.domain.auth.models.RegisterModel
import ru.connect.domain.auth.models.SessionToken
import ru.connect.domain.auth.models.UserIsuProfile

interface AuthDataSource {

    suspend fun sendIsuNumber(isuNumber: String)

    suspend fun login(isuNumber: String, otpCode: String): SessionToken
    suspend fun isRegistered(isuNumber: String): Boolean
    suspend fun register(request: RegisterModel): SessionToken
    suspend fun requestProfilledProfile(isuNumber: String, otpCode: String): UserIsuProfile
}
