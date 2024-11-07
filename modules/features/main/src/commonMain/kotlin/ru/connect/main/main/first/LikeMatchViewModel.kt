package ru.connect.main.main.first

import kotlinx.coroutines.Job
import org.koin.android.annotation.KoinViewModel
import ru.connect.core.extensions.launchCatching
import ru.connect.core.state.LoadingState
import ru.connect.core.state.error
import ru.connect.core.state.idle
import ru.connect.core.state.loading
import ru.connect.core.state.success
import ru.connect.core.ui.UdfViewModel
import ru.connect.domain.recommendations.RecommendationInteractor
import ru.connect.domain.recommendations.models.ProfileDto

@KoinViewModel(binds = [])
class LikeMatchViewModel(
    private val recommendationInteractor: RecommendationInteractor,
) : UdfViewModel<LoadingState<LikeMatchUi, Unit>, LikeMatchNavigationTarget>(idle()) {

    private var profiles: List<ProfileDto> = emptyList()
    private var loadingJob: Job? = null

    init {
        loadScreen()
    }

    fun onRefresh() {
        loadScreen()
    }

    private fun loadScreen() {
        launchCatching(
            tryBlock = {
                _state.updateUi { loading() }

                val images = recommendationInteractor.fetch().reversed()

                _state.updateUi {
                    LikeMatchUi(
                        profile = images.map {
                            LikeMatchUi.ProfileUi(
                                images = listOf(it.avatarUrl) + it.photos,
                                name = it.name,
                                facultet = it.faculty.shortName,
                                aboutMe = it.about,
                                id = it.id,
                                tags = it.tags.map {
                                    LikeMatchUi.TagUi(id = it.uuid, name = it.name)
                                }
                            )
                        }
                    ).success()
                }
            }, catchBlock = {
                _state.updateUi { Unit.error() }
            }
        )
    }

    fun onLike(profileId: String) {
        launchCatching {
            _state.updateUi {
                copy { ui -> ui.copy(profile = ui.profile.filter { it.id != profileId }) }
            }

            profiles = profiles.filter { it.id != profileId }
            launchCatching { recommendationInteractor.like(profileId) }

            if (profiles.size <= MAX_COUNT) {
                loadProfiles()
            }
        }
    }

    fun onDisLike(profileId: String) {
        launchCatching {
            _state.updateUi {
                copy { ui -> ui.copy(profile = ui.profile.filter { it.id != profileId }) }
            }

            profiles = profiles.filter { it.id != profileId }
            launchCatching { recommendationInteractor.dislike(profileId) }

            if (profiles.size <= MAX_COUNT) {
                loadProfiles()
            }
        }
    }

    private fun loadProfiles() {
        if (loadingJob?.isActive == true) return

        loadingJob = launchCatching {
            val profilesRes = recommendationInteractor.fetch().reversed()
            profiles = profilesRes + profiles

            _state.updateUi {
                copy { ui ->
                    ui.copy(
                        profile = profiles.map {
                            LikeMatchUi.ProfileUi(
                                images = listOf(it.avatarUrl) + it.photos,
                                name = it.name,
                                facultet = it.faculty.shortName,
                                aboutMe = it.about,
                                id = it.id,
                                tags = it.tags.map {
                                    LikeMatchUi.TagUi(id = it.uuid, name = it.name)
                                }
                            )
                        }
                    )
                }
            }
        }
    }

    private val MAX_COUNT = 5
}
