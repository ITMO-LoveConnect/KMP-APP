package ru.connect.domain.recommendations

import org.koin.core.annotation.Provided
import org.koin.core.annotation.Singleton
import ru.connect.domain.recommendations.models.ProfileDto

@Singleton
class RecommendationQueueRepository(
    @Provided private val dataSource: RecommendationQueueDataSource
) {

    suspend fun fetch(): List<ProfileDto> = dataSource.fetch()
    suspend fun dislike(profileId: String) = dataSource.dislike(profileId)
    suspend fun like(profileId: String) = dataSource.like(profileId)
}
