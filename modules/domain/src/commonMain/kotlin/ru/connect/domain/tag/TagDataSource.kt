package ru.connect.domain.tag

import ru.connect.core.cache.RemoteDataSource
import ru.connect.domain.tag.models.TagEntity

interface TagDataSource : RemoteDataSource<Unit, List<TagEntity>>
