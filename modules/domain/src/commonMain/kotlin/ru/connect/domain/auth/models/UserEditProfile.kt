package ru.connect.domain.auth.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class UserEditProfile(
    val name: String,
    val faculty: Faculty,
    val gender: Gender,
    val datingPurpose: DatingPurpose,
    val birthday: LocalDate,
    val tagsIds: List<String>,
)
