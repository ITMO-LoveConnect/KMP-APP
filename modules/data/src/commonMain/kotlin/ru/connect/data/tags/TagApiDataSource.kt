package ru.connect.data.tags

import org.koin.core.annotation.Singleton
import ru.connect.domain.tag.TagDataSource
import ru.connect.domain.tag.models.TagCategory
import ru.connect.domain.tag.models.TagEntity

@Singleton
class TagApiDataSource : TagDataSource {
    override suspend fun fetch(request: Unit): List<TagEntity> {
        return listOf(
            // ART category
            TagEntity("1", "Photography", TagCategory.ART),
            TagEntity("2", "Video Production", TagCategory.ART),
            TagEntity("3", "Design", TagCategory.ART),
            TagEntity("4", "Makeup", TagCategory.ART),
            TagEntity("5", "Handicraft", TagCategory.ART),
            TagEntity("6", "Dance", TagCategory.ART),
            TagEntity("7", "Singing", TagCategory.ART),
            TagEntity("8", "Music", TagCategory.ART),
            TagEntity("9", "Blogging", TagCategory.ART),
            TagEntity("10", "Drawing", TagCategory.ART),

            // SPORT category
            TagEntity("11", "Football", TagCategory.SPORT),
            TagEntity("12", "Swimming", TagCategory.SPORT),
            TagEntity("13", "Cycling", TagCategory.SPORT),
            TagEntity("14", "Volleyball", TagCategory.SPORT),
            TagEntity("15", "Basketball", TagCategory.SPORT),
            TagEntity("16", "Hockey", TagCategory.SPORT),
            TagEntity("17", "Track and Field", TagCategory.SPORT),
            TagEntity("18", "Weightlifting", TagCategory.SPORT),
            TagEntity("19", "Boxing", TagCategory.SPORT),
            TagEntity("20", "Figure Skating", TagCategory.SPORT),
            TagEntity("21", "Gymnastics", TagCategory.SPORT),
            TagEntity("22", "Ski Racing", TagCategory.SPORT),
            TagEntity("23", "Esports", TagCategory.SPORT),
            TagEntity("24", "Yo-yo", TagCategory.SPORT),

            // MUSIC category
            TagEntity("25", "Rock", TagCategory.MUSIC),
            TagEntity("26", "Jazz", TagCategory.MUSIC),
            TagEntity("27", "Classical", TagCategory.MUSIC),
            TagEntity("28", "Pop", TagCategory.MUSIC),
            TagEntity("29", "Hip-Hop", TagCategory.MUSIC),

            // PETS category
            TagEntity("30", "Cats", TagCategory.PETS),
            TagEntity("31", "Dogs", TagCategory.PETS),
            TagEntity("32", "Birds", TagCategory.PETS),
            TagEntity("33", "Fish", TagCategory.PETS),
            TagEntity("34", "Rabbits", TagCategory.PETS),
            TagEntity("35", "Turtles", TagCategory.PETS),
            TagEntity("36", "Snakes", TagCategory.PETS),
            TagEntity("37", "Lizards", TagCategory.PETS),
            TagEntity("38", "Hamsters", TagCategory.PETS),

            // SOCIAL_LIFE category
            TagEntity("39", "Cinemas", TagCategory.SOCIAL_LIFE),
            TagEntity("40", "Concerts and Shows", TagCategory.SOCIAL_LIFE),
            TagEntity("41", "Museums and Galleries", TagCategory.SOCIAL_LIFE),
            TagEntity("42", "Theaters", TagCategory.SOCIAL_LIFE),
            TagEntity("43", "Outdoor Recreation", TagCategory.SOCIAL_LIFE),
            TagEntity("44", "Festivals", TagCategory.SOCIAL_LIFE),
            TagEntity("45", "Parties and Clubs", TagCategory.SOCIAL_LIFE),
            TagEntity("46", "Volunteering", TagCategory.SOCIAL_LIFE),
            TagEntity("47", "Restaurants", TagCategory.SOCIAL_LIFE),
            TagEntity("48", "Travel", TagCategory.SOCIAL_LIFE),
            TagEntity("49", "Shopping", TagCategory.SOCIAL_LIFE),
            TagEntity("50", "Meeting Friends", TagCategory.SOCIAL_LIFE),
            TagEntity("51", "Art", TagCategory.SOCIAL_LIFE),
            TagEntity("52", "Active Recreation", TagCategory.SOCIAL_LIFE)
        )
    }
}
