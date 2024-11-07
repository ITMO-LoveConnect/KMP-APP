package ru.connect.domain.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class RegisterModel(
    val isuNumber: String,
    val confirmationCode: Long,
    val profile: UserEditProfile,
)
