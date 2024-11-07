package ru.connect.domain.recommendations

import ru.connect.domain.recommendations.models.ProfileDto

interface RecommendationQueueDataSource {
    suspend fun fetch(): List<ProfileDto>
    suspend fun like(profileId: String)
    suspend fun dislike(profileId: String)
}
