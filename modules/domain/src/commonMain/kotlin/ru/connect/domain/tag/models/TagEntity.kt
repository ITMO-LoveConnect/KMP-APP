package ru.connect.domain.tag.models

import kotlinx.serialization.Serializable

@Serializable
data class TagEntity(
    val uuid: String,
    val name: String,
    val category: TagCategory,
)
