package ru.connect.profile.create

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.ImageBitmap
import ru.connect.domain.auth.models.DatingPurpose
import ru.connect.domain.tag.models.TagCategory

@Immutable
data class ProfileCreateUi(
    val name: String,
    val gender: String,
    val faculty: String,
    val course: String,

    val birthday: String = "Не выбрано",
    val about: String = "",
    val datingPurpose: String? = null,
    val selectedDatingPurpose: DatingPurpose = DatingPurpose.FRIENDSHIP,
    val groups: List<GroupsTagUi> = emptyList(),

    val images: List<ImageCardUi> = listOf(ImageCardUi.Add),
    val isButtonEnabled: Boolean = false,
    val isButtonInProgress: Boolean = false,
) {

    @Immutable
    data class GroupsTagUi(
        val name: String,
        val type: TagCategory,
        val tags: List<TagUi>,
    )

    @Immutable
    data class TagUi(val id: String, val name: String, val isSelected: Boolean = false)

    @Immutable
    sealed class ImageCardUi {
        data object Add : ImageCardUi()
        data class Image(val bitmap: ImageBitmap) : ImageCardUi()
    }
}
