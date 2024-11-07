package ru.connect.domain.recommendations.models

import ru.connect.domain.auth.models.DatingPurpose
import ru.connect.domain.auth.models.Faculty
import ru.connect.domain.auth.models.Gender
import ru.connect.domain.tag.models.TagEntity

data class ProfileDto(
    val id: String,
    val name: String,
    val gender: Gender,
    val avatarUrl: String,
    val photos: List<String>,
    val age: Short,
    val faculty: Faculty,
    val course: Short,
    val about: String,
    val datingPurpose: DatingPurpose,
    val tags: List<TagEntity>
)
