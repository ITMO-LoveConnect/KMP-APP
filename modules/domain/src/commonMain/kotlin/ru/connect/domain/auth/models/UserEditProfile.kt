package ru.connect.domain.auth.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class UserEditProfile(
    val purpose: DatingPurpose,
    val birthday: LocalDate,
    val tagsIds: List<String>,
    val images: List<ByteArray>,
)
