package ru.connect.domain.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class Session(
    val token: String,
)
