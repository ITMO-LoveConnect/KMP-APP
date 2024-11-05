package ru.connect.domain.feature1

import ru.connect.core.cache.RemoteDataSource
import ru.connect.domain.feature1.models.Feature1Model

interface Feature1DataSource : RemoteDataSource<Unit, Feature1Model>
