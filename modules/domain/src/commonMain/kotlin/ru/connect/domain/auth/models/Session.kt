package ru.connect.domain.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class Session(
    val userId: String,
    val token: String,
    val isNewUser: Boolean,
    val userIsuProfile: UserIsuProfile,
)
