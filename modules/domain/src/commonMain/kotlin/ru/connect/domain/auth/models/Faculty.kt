package ru.connect.domain.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class Faculty(
    val id: String,
    val shortName: String,
)
