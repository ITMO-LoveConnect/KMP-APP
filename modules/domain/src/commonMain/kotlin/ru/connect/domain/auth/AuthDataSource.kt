package ru.connect.domain.auth

import ru.connect.domain.auth.models.Session

interface AuthDataSource {

    suspend fun sendIsuNumber(isuNumber: String)

    suspend fun sendOtpCode(isuNumber: String, otpCode: String): Session
}
