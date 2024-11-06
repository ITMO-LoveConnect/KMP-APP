package ru.connect.domain.session

import org.koin.core.annotation.Singleton
import ru.connect.core.cache.CacheEntry
import ru.connect.domain.auth.models.UserIsuProfile

@Singleton
class SessionRepository(
    private val dataSource: SessionLocalDataSource,
) {
    suspend fun set(profile: UserIsuProfile) {
        dataSource.set(Unit, CacheEntry(Unit, profile))
    }

    suspend fun get(): UserIsuProfile = requireNotNull(dataSource.get(Unit)?.value)
}
