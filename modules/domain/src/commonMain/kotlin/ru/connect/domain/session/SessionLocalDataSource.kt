package ru.connect.domain.session

import org.koin.core.annotation.Singleton
import ru.connect.core.cache.InMemoryLocalDataSource
import ru.connect.domain.auth.models.Session

@Singleton
class SessionLocalDataSource : InMemoryLocalDataSource<Unit, Session>()
