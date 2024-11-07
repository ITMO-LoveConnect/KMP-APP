package ru.connect.domain.session

import org.koin.core.annotation.Singleton
import ru.connect.core.cache.CacheEntry

@Singleton
class SessionRepository(
    private val dataSource: SessionLocalDataSource,
) {
    // isu, otp
    suspend fun set(session: Pair<String, String>) {
        dataSource.set(Unit, CacheEntry(Unit, session))
    }

    suspend fun get(): Pair<String, String> = requireNotNull(dataSource.get(Unit)?.value)
}
