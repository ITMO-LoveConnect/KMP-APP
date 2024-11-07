package ru.connect.domain.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class UserIsuProfile(
    val fullName: String,
    val gender: Gender,
    val faculty: Faculty,
    val group: String,
    val course: Long,
)
