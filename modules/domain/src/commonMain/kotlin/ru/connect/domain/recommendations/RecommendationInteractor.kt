package ru.connect.domain.recommendations

import org.koin.core.annotation.Singleton

@Singleton
class RecommendationInteractor(
    private val queueRepository: RecommendationQueueRepository
) {
    suspend fun fetch() = queueRepository.fetch()
    suspend fun dislike(profileId: String) = queueRepository.dislike(profileId)

    suspend fun like(profileId: String) = queueRepository.like(profileId)
}
