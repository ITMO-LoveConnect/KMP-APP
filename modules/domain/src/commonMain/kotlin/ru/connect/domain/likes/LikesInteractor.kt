package ru.connect.domain.likes

import org.koin.core.annotation.Singleton
import ru.connect.core.cache.CachePolicy
import ru.connect.domain.likes.models.GetReactionDto

@Singleton
class LikesInteractor(
    private val likesRepository: LikesRepository,
) {

    suspend fun fetchLikes(): List<GetReactionDto> = likesRepository.fetch(Unit, CachePolicy.REFRESH).orEmpty()
}
