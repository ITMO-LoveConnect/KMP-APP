package ru.connect.main.main.first

import androidx.compose.runtime.Immutable

@Immutable
data class LikeMatchUi(
    val profile: List<ProfileUi>
) {

    @Immutable
    data class ProfileUi(
        val id: String,
        val images: List<String>,
        val name: String,
        val facultet: String,
        val aboutMe: String,
        val tags: List<TagUi>
    )

    @Immutable
    data class TagUi(val id: String, val name: String, val isSelected: Boolean = false)
}
