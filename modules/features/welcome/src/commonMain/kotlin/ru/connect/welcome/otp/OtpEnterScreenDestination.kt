package ru.connect.welcome.otp

import kotlinx.serialization.Serializable

@Serializable
data class OtpEnterScreenDestination(
    val isuNumber: String
)
