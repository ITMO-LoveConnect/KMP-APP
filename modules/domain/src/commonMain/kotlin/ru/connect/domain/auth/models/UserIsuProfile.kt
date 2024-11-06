package ru.connect.domain.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class UserIsuProfile(
    val name: String,
    val gender: Gender,
    val faculty: Faculty,
    val course: Long,
)
