package ru.connect.domain.auth.models

import kotlinx.serialization.Serializable

@Serializable
class SessionToken(
    val token: String
)
