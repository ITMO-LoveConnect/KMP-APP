package ru.connect.domain.session

import org.koin.core.annotation.Singleton
import ru.connect.core.cache.InMemoryLocalDataSource

@Singleton
class SessionLocalDataSource : InMemoryLocalDataSource<Unit, Pair<String, String>>()
