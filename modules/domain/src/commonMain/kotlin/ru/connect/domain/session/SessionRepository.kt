package ru.connect.domain.session

import org.koin.core.annotation.Singleton
import ru.connect.core.cache.CacheEntry
import ru.connect.domain.auth.models.Session

@Singleton
class SessionRepository(
    private val dataSource: SessionLocalDataSource,
) {
    suspend fun set(session: Session) {
        dataSource.set(Unit, CacheEntry(Unit, session))
    }

    suspend fun get(): Session = requireNotNull(dataSource.get(Unit)?.value)
}
