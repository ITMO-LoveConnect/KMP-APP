package ru.connect.data.feature1

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.koin.core.annotation.Singleton
import ru.connect.domain.feature1.Feature1DataSource
import ru.connect.domain.feature1.models.Feature1Model

@Singleton
class Feature1ApiDataSource(
    private val client: HttpClient,
) : Feature1DataSource {
    override suspend fun fetch(request: Unit): Feature1Model = client.get(
        urlString = "https://catfact.ninja/fact"
    ).body()
}
