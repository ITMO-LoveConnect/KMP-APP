package ru.connect.domain.likes

import org.koin.core.annotation.Provided
import org.koin.core.annotation.Singleton
import ru.connect.core.cache.CachePolicyRepository
import ru.connect.core.cache.InMemoryLocalDataSource
import ru.connect.domain.likes.models.GetReactionDto
import ru.connect.domain.likes.models.LikesDataSource

@Singleton
class LikesRepository(
    @Provided private val dataSource: LikesDataSource,
) : CachePolicyRepository<Unit, List<GetReactionDto>>(
    localDataSource = InMemoryLocalDataSource(),
    remoteDataSource = dataSource,
)
