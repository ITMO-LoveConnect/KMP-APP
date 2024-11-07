package ru.connect.domain.tag

import org.koin.core.annotation.Provided
import org.koin.core.annotation.Singleton
import ru.connect.core.cache.CachePolicyRepository
import ru.connect.core.cache.InMemoryLocalDataSource
import ru.connect.domain.tag.models.TagEntity

@Singleton
class TagRepository(
    @Provided private val dataSource: TagDataSource,
) : CachePolicyRepository<Unit, List<TagEntity>>(
    localDataSource = InMemoryLocalDataSource(),
    remoteDataSource = dataSource,
)
