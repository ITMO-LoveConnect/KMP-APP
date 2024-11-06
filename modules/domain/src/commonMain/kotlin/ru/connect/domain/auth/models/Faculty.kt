package ru.connect.domain.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class Faculty(
    val uuid: String,
    val shortName: String,
)
