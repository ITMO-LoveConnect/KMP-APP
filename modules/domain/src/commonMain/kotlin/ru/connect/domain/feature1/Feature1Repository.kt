package ru.connect.domain.feature1

import org.koin.core.annotation.Provided
import org.koin.core.annotation.Singleton
import ru.connect.core.cache.CachePolicyRepository
import ru.connect.core.cache.InMemoryLocalDataSource
import ru.connect.domain.feature1.models.Feature1Model

@Singleton
class Feature1Repository(
    @Provided private val remoteDataSource: Feature1DataSource,
) : CachePolicyRepository<Unit, Feature1Model>(
    localDataSource = InMemoryLocalDataSource(),
    remoteDataSource = remoteDataSource
)
