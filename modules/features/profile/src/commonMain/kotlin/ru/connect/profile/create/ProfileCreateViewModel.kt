package ru.connect.profile.create

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap
import org.koin.android.annotation.KoinViewModel
import ru.connect.core.extensions.launchCatching
import ru.connect.core.state.LoadingState
import ru.connect.core.state.error
import ru.connect.core.state.idle
import ru.connect.core.state.loading
import ru.connect.core.state.success
import ru.connect.core.ui.UdfViewModel
import ru.connect.domain.auth.AuthInteractor
import ru.connect.domain.auth.models.DatingPurpose
import ru.connect.domain.auth.models.Gender
import ru.connect.domain.auth.models.RegisterModel
import ru.connect.domain.auth.models.UserEditProfile
import ru.connect.domain.tag.models.TagCategory
import ru.connect.domain.tag.models.TagEntity

@KoinViewModel(binds = [])
class ProfileCreateViewModel(
    private val authInteractor: AuthInteractor,
) : UdfViewModel<LoadingState<ProfileCreateUi, Unit>, ProfileCreateNavigationTarget>(idle()) {

    private var images: List<ByteArray> = emptyList()
    private var selectedTags: List<TagEntity> = emptyList()
    private var birthday: LocalDate? = null
    private var purpose: DatingPurpose = DatingPurpose.FRIENDSHIP
    private var tags: Deferred<List<TagEntity>> = async(start = CoroutineStart.LAZY) {
        authInteractor.getTags()
    }

    init {
        loadScreen()
    }

    fun onRefresh() {
        loadScreen()
    }

    fun navigateUp() {
        _state.navigateTo(ProfileCreateNavigationTarget.Back)
    }

    private fun loadScreen() {
        launchCatching(
            tryBlock = {
                _state.updateUi { loading() }
                val profile = authInteractor.requestProfilledProfileBase()
                val tags = tags.await()

                _state.updateUi {
                    ProfileCreateUi(
                        name = profile.fullName,
                        gender = when (profile.gender) {
                            Gender.MALE -> "Мужской"
                            Gender.FEMALE -> "Женский"
                        },
                        faculty = profile.faculty.shortName,
                        course = profile.course.toString(),
                        groups = mapTagsToGroups(tags),
                        selectedDatingPurpose = purpose
                    ).success()
                }
                updateButtonState()
            }, catchBlock = { throwable ->
                _state.updateUi { Unit.error() }
            }
        )
    }

    @OptIn(ExperimentalResourceApi::class)
    fun onFileSelect(bytes: ByteArray) {
        launchCatching {
            if (images.size < MAX_COUNT_IMAGES) {
                val bitmap = bytes.decodeToImageBitmap()
                images = images + bytes
                _state.updateUi {
                    copy { ui ->
                        ui.copy(images = (ui.images.filter { it !is ProfileCreateUi.ImageCardUi.Add } + ProfileCreateUi.ImageCardUi.Image(
                            bitmap
                        ) + ProfileCreateUi.ImageCardUi.Add.takeIf { images.size < MAX_COUNT_IMAGES }).filterNotNull())
                    }
                }
            }
            updateButtonState()
        }
    }

    fun onDateSelect(l: Long?) {
        launchCatching {
            l?.let {
                _state.updateUi { copy { ui -> ui.copy(birthday = convertMillisToDate(it)) } }
            }
            updateButtonState()
        }
    }

    fun onRemove(pos: Int) {
        launchCatching {
            if (images.size < MAX_COUNT_IMAGES) {
                val mut = images.toMutableList().apply { removeAt(pos) }
                images = mut
                _state.updateUi {
                    copy { ui ->
                        ui.copy(
                            images = (ui.images.toMutableList()
                                .apply { removeAt(pos) }
                                .filter { it !is ProfileCreateUi.ImageCardUi.Add } +
                                    ProfileCreateUi.ImageCardUi.Add.takeIf { images.size < MAX_COUNT_IMAGES }).filterNotNull()
                        )
                    }
                }
            }
            updateButtonState()
        }
    }

    fun mapTagsToGroups(tags: List<TagEntity>, selectedTagIds: Set<String> = emptySet()): List<ProfileCreateUi.GroupsTagUi> {
        return tags
            .groupBy { it.category } // Группируем по категориям
            .map { (category, tagsInCategory) ->
                ProfileCreateUi.GroupsTagUi(
                    name = category.toReadableName(), // Преобразуем категорию в строковое представление
                    type = category,
                    tags = tagsInCategory.map { tag ->
                        ProfileCreateUi.TagUi(
                            id = tag.uuid,
                            name = tag.name,
                            isSelected = selectedTagIds.contains(tag.uuid) // Устанавливаем isSelected, если ID тега находится в selectedTagIds
                        )
                    }
                )
            }
    }

    // Функция для преобразования TagCategory в человекочитаемую строку
    fun TagCategory.toReadableName(): String {
        return when (this) {
            TagCategory.ART -> "Искусство"
            TagCategory.SPORT -> "Спорт"
            TagCategory.MUSIC -> "Музыка"
            TagCategory.PETS -> "Домашние животные"
            TagCategory.SOCIAL_LIFE -> "Социальная жизнь"
        }
    }

    private fun convertMillisToDate(millis: Long): String {
        Instant.fromEpochMilliseconds(millis)
        val localDateTime = Instant.fromEpochMilliseconds(millis).toLocalDateTime(TimeZone.currentSystemDefault())
        val year = localDateTime.year
        val month = localDateTime.monthNumber.toString().padStart(2, '0')
        val day = localDateTime.dayOfMonth.toString().padStart(2, '0')

        birthday = LocalDate(localDateTime.year, localDateTime.monthNumber, localDateTime.dayOfMonth)

        return "$day.$month.$year"
    }

    fun onTagClick(tagCategory: TagCategory, s: String) {
        launchCatching {
            val tags = tags.await()
            selectedTags = selectedTags.filter { it.category != tagCategory } + tags.first { it.uuid == s }

            _state.updateUi {
                copy { ui ->
                    ui.copy(
                        groups = ui.groups.map { group ->
                            if (group.type == tagCategory) group.copy(tags = group.tags.map { t ->
                                if (t.id == s) t.copy(isSelected = true) else t.copy(
                                    isSelected = false
                                )
                            })
                            else group
                        }
                    )
                }
            }
        }
    }

    private fun updateButtonState() {
        _state.updateUi {
            copy { ui ->
                ui.copy(
                    isButtonEnabled = birthday != null && images.isNotEmpty()
                )
            }
        }
    }

    fun onClickButton() {
        launchCatching(
            tryBlock = {
                _state.updateUi { copy { ui -> ui.copy(isButtonInProgress = true) } }
                val pair = authInteractor.getPairsIsuOtp()
                val profile = authInteractor.requestProfilledProfileBase()
                val request = RegisterModel(
                    isuNumber = pair.first,
                    confirmationCode = pair.second.toLong(),
                    profile = UserEditProfile(
                        name = profile.fullName,
                        faculty = profile.faculty,
                        datingPurpose = DatingPurpose.FRIENDSHIP,
                        birthday = requireNotNull(birthday),
                        tagsIds = emptyList(),
                        gender = Gender.MALE,
                    ),
                )
                authInteractor.register(request)
                _state.navigateTo(ProfileCreateNavigationTarget.MainScreen)
            }, finalBlock = {
                _state.updateUi { copy { ui -> ui.copy(isButtonInProgress = false) } }
            }
        )
    }

    companion object {
        const val MAX_COUNT_IMAGES = 5
    }
}
