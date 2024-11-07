package ru.connect.main.main.second

import org.koin.android.annotation.KoinViewModel
import ru.connect.core.extensions.launchCatching
import ru.connect.core.state.LoadingState
import ru.connect.core.state.error
import ru.connect.core.state.idle
import ru.connect.core.state.loading
import ru.connect.core.state.success
import ru.connect.core.ui.UdfViewModel
import ru.connect.domain.likes.LikesInteractor
import ru.connect.domain.likes.models.LikeStatus

@KoinViewModel(binds = [])
class MatchesTabViewModel(
    private val likesInteractor: LikesInteractor,
) : UdfViewModel<LoadingState<MatchesTabUi, Unit>, MatchesTabNavigationTarget>(idle()) {

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
                val matches = likesInteractor.fetchLikes()

                val metched = matches.filter { it.likeStatus == LikeStatus.MUTUAL }
                val single = matches.filter { it.likeStatus == LikeStatus.RECEIVED }

                _state.updateUi {
                    MatchesTabUi(
                        matched = metched.map { MatchesTabUi.Card(id = it.likedById, image = it.avatarUrl) },
                        likedYou = single.map { MatchesTabUi.Card(id = it.likedById, image = it.avatarUrl) },
                    ).success()
                }
            }, catchBlock = { throwable ->
                throwable.printStackTrace()
                _state.updateUi { Unit.error() }
            }
        )
    }
}
