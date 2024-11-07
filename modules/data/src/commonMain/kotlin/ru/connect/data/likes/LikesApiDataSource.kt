package ru.connect.data.likes

import io.ktor.client.HttpClient
import org.koin.core.annotation.Named
import org.koin.core.annotation.Singleton
import ru.connect.domain.likes.models.GetReactionDto
import ru.connect.domain.likes.models.LikesDataSource

@Singleton
class LikesApiDataSource(
    @Named("AUTHED") private val client: HttpClient
) : LikesDataSource {
    override suspend fun fetch(request: Unit): List<GetReactionDto> {
        return emptyList()
        //        return client.get("likes").body()
    }
}
