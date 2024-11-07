package ru.connect.data.recommendations

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable
import org.koin.core.annotation.Named
import org.koin.core.annotation.Singleton
import ru.connect.domain.auth.models.DatingPurpose
import ru.connect.domain.auth.models.Faculty
import ru.connect.domain.auth.models.Gender
import ru.connect.domain.recommendations.RecommendationQueueDataSource
import ru.connect.domain.recommendations.models.ProfileDto
import ru.connect.domain.tag.models.TagCategory
import ru.connect.domain.tag.models.TagEntity

@Singleton
class RecommendationQueueDataSourceImpl(
    @Named("AUTHED")
    private val client: HttpClient
) : RecommendationQueueDataSource {
    override suspend fun fetch(): List<ProfileDto> = buildList {
        add(
            ProfileDto(
                id = "UUID.randomUUID(4)",
                name = "Иван",
                gender = Gender.MALE,
                avatarUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ed/Elon_Musk_Royal_Society.jpg/533px-Elon_Musk_Royal_Society.jpg",
                photos = listOf(),
                age = 40,
                course = 99, // "Профессор обаяния"
                about = "Охае, я фронтендер, учусь в ИТМО (хотя по этому приложению итак понятно)",
                datingPurpose = DatingPurpose.FRIENDSHIP,
                faculty = Faculty("", "Acting and Performing Arts"),
                tags = listOf(
                    TagEntity("2", "Джаз 🎷", TagCategory.MUSIC),
                    TagEntity("3", "Скейтбординг 🛹", TagCategory.SPORT),
                    TagEntity("4", "Собаки 🐶", TagCategory.PETS),
                    TagEntity("5", "Концерты 🎶", TagCategory.SOCIAL_LIFE),
                    TagEntity("6", "Вечера в уютных барах 🥂", TagCategory.SOCIAL_LIFE)
                )
            )
        )
        add(
            ProfileDto(
                id = "UUID.randomUUID(14)",
                name = "Камрон",
                gender = Gender.MALE,
                avatarUrl = "https://ryanschultz.com/wp-content/uploads/2018/03/young-man-2939344_1920.jpg",
                photos = listOf(),
                age = 40,
                course = 99, // "Профессор обаяния"
                about =
                "Каееееееф",
                datingPurpose = DatingPurpose.FRIENDSHIP,
                faculty = Faculty("", "Acting and Performing Arts"),
                tags = listOf(
                    TagEntity("1", "Актёрское мастерство 🎬", TagCategory.ART),
                    TagEntity("2", "Джаз 🎷", TagCategory.MUSIC),
                    TagEntity("3", "Скейтбординг 🛹", TagCategory.SPORT),
                    TagEntity("4", "Собаки 🐶", TagCategory.PETS),
                    TagEntity("5", "Концерты 🎶", TagCategory.SOCIAL_LIFE),
                    TagEntity("6", "Вечера в уютных барах 🥂", TagCategory.SOCIAL_LIFE)
                )
            )
        )
        add(
            ProfileDto(
                id = "UUID.randomUUID(3)",
                name = "Артем",
                gender = Gender.MALE,
                avatarUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/04/Elon_Musk_-_The_Summit_2013.jpg/400px-Elon_Musk_-_The_Summit_2013.jpg",
                photos = listOf(),
                age = 52,
                course = 99, // Вечный студент Вселенной!
                about = "Гениальный изобретатель и предприниматель с мечтой о Марсе и заправке в каждом уголке галактики! 🚀 Люблю большие идеи, электрические машины и долгие ночные разговоры о будущем человечества. В поиске умных собеседников и новых вызовов. Если ты готова взлететь со мной, добро пожаловать на борт! 🌌🚀",
                datingPurpose = DatingPurpose.FRIENDSHIP,
                faculty = Faculty("", "Space Exploration"),
                tags = listOf(
                    TagEntity("1", "Рисование 🚀", TagCategory.ART),
                    TagEntity("2", "Космические гонки 🚀", TagCategory.SPORT),
                    TagEntity("3", "Техно 🎶", TagCategory.MUSIC),
                    TagEntity("4", "Кот-астронавт 🐱", TagCategory.PETS),
                    TagEntity("5", "Фестивали инноваций 🎉", TagCategory.SOCIAL_LIFE),
                    TagEntity("6", "Тусовки в техноклубах 💃", TagCategory.SOCIAL_LIFE)
                )
            )
        )
        add(
            ProfileDto(
                id = "UUID.randomUUID(2)",
                name = "Пашенька Дуров",
                gender = Gender.MALE,
                avatarUrl = "https://cdn5.vedomosti.ru/image/2018/31/owlwb/original-wa0.jpg",
                photos = listOf(
                    "https://cdnstatic.rg.ru/uploads/images/143/90/29/durov-tors-1000.jpg"
                ),
                age = 25,
                course = 3,
                about = "Я Пашенька, техномагнат с жаждой приключений, самопознания и эпичных селфи! 🕶️ Днем пишу код, на рассвете катаюсь на коньках, а ночью философствую под звездами. Здесь, чтобы завести настоящие знакомства, повеселиться и исследовать, что жизнь может предложить. Кто знает, может, ты станешь частью моего следующего приключения? 🌍💻",
                datingPurpose = DatingPurpose.FRIENDSHIP,
                faculty = Faculty("", "Name faculty"),
                tags = listOf(
                    TagEntity("9", "Блогинг 📸", TagCategory.ART),
                    TagEntity("20", "Фигурное катание ⛸️", TagCategory.SPORT),
                    TagEntity("43", "Отдых на природе 🏕️", TagCategory.SOCIAL_LIFE),
                    TagEntity("44", "Фестивали 🎉", TagCategory.SOCIAL_LIFE),
                    TagEntity("45", "Тусовки и клубы 💃", TagCategory.SOCIAL_LIFE),
                    TagEntity("46", "Волонтёрство 👐", TagCategory.SOCIAL_LIFE)
                )
            )
        )

        add(
            ProfileDto(
                id = "UUID.randomUUID(5)",
                name = "Абу",
                gender = Gender.MALE,
                avatarUrl = "https://i2.wp.com/wallpaperaccess.com/full/1137910.jpg",
                photos = listOf(),
                age = 27,
                course = 2,
                about = "Готов всегда и везде сделать селфи! 📸 Обожаю качалку, свою тачку и обтягивающие футболки. Каждый день – новый повод для зеркального селфи. Если ты тоже считаешь, что важнее внешнего вида нет ничего – напиши мне! 💪",
                datingPurpose = DatingPurpose.FRIENDSHIP,
                faculty = Faculty("", "Selfie Studies"),
                tags = listOf(
                    TagEntity("1", "Фотография 📸", TagCategory.ART),
                    TagEntity("2", "Фитнес 💪", TagCategory.SPORT),
                    TagEntity("3", "Качалка 🏋️", TagCategory.SPORT),
                    TagEntity("4", "Котики 🐱", TagCategory.PETS),
                    TagEntity("5", "Клубы и тусовки 🎉", TagCategory.SOCIAL_LIFE),
                    TagEntity("6", "Концерты 🎶", TagCategory.SOCIAL_LIFE)
                )
            )
        )
    }

    override suspend fun like(profileId: String) {
        client.post("likes") {
            contentType(ContentType.Application.Json)
            setBody(
                ReactionDto(
                    likedUserId = profileId,
                    type = ReactionType.LIKE,
                    viewed = Clock.System.now().toString(),
                )
            )
        }
    }

    override suspend fun dislike(profileId: String) {
        client.post("likes") {
            contentType(ContentType.Application.Json)
            setBody(
                ReactionDto(
                    likedUserId = profileId,
                    type = ReactionType.DISLIKE,
                    viewed = Clock.System.now().toString(),
                )
            )
        }
    }

    @Serializable
    data class ReactionDto(
        val likedUserId: String,
        val type: ReactionType,
        val message: String? = null,
        val viewed: String
    )

    @Serializable
    enum class ReactionType {
        LIKE,
        DISLIKE // Add more types if needed
    }
}
