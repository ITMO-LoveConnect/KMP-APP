package ru.connect.domain.likes.models

import ru.connect.core.cache.RemoteDataSource

interface LikesDataSource : RemoteDataSource<Unit, List<GetReactionDto>>
