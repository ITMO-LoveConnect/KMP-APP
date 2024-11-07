package ru.connect.domain.likes.models

import kotlinx.serialization.Serializable

@Serializable
data class GetReactionDto(
    val likedById: String,
    val likeStatus: LikeStatus,
    val avatarUrl: String,
    val viewed: String,
    val likedTime: String
)

@Serializable
enum class LikeStatus {
    MUTUAL,
    RECEIVED
}
