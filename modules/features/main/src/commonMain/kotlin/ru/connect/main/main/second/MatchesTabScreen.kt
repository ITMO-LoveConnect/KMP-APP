package ru.connect.main.main.second

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.koin.compose.viewmodel.koinViewModel
import ru.connect.core.extensions.onError
import ru.connect.core.extensions.onLoading
import ru.connect.core.extensions.onSuccess
import ru.connect.core.ui.error.ErrorScreen
import ru.connect.core.ui.extensions.maxFullScreenWidth
import ru.connect.core.ui.loading.CircularProgressIndicatorLoadingScreen
import ru.connect.core.ui.theme.ConnectShapes
import ru.connect.core.ui.theme.ConnectTheme

@Composable
internal fun MatchesTabScreen(
    viewModel: MatchesTabViewModel = koinViewModel()
) {
    val uiState = viewModel.state.collectAsState().value
    val ui = uiState.model

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Ваши лайки"
                    )
                },
                backgroundColor = ConnectTheme.colors.material.background,
            )
        }
    ) {

        ui.onSuccess {
            Content(
                ui = data,
            )
        }

        ui.onLoading { CircularProgressIndicatorLoadingScreen() }

        ui.onError {
            ErrorScreen(onRefresh = viewModel::onRefresh)
        }
    }
}

@Composable
private fun Content(
    ui: MatchesTabUi,
) {
    val context = LocalPlatformContext.current
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .maxFullScreenWidth()
            .padding(horizontal = 16.dp)
            .padding(vertical = 24.dp),
    ) {
        if (ui.matched.isEmpty() && ui.likedYou.isEmpty()) {
            ErrorScreen(
                message = "Отсутствуют лайки вам"
            )
        }

        if (ui.matched.isNotEmpty()) {
            Text(text = "Ваши совпадения", style = ConnectTheme.typography.material.h5)
            ui.matched.chunked(2).forEach { res ->
                Row(
                    modifier = Modifier.fillMaxWidth().height(180.dp)
                ) {
                    AsyncImage(
                        modifier = Modifier.width(120.dp).padding(horizontal = 16.dp, vertical = 24.dp)
                            .clip(ConnectShapes.RoundedCornerShape12),
                        model = ImageRequest.Builder(context)
                            .data(res.first())
                            .crossfade(true) // Enables crossfade transition
                            .build(),
                        contentDescription = null, // replace with your content description,
                        alignment = Alignment.TopCenter,
                    )

                    if (res.size > 1) {
                        AsyncImage(
                            modifier = Modifier.width(120.dp).padding(horizontal = 16.dp, vertical = 24.dp)
                                .clip(ConnectShapes.RoundedCornerShape12),
                            model = ImageRequest.Builder(context)
                                .data(res[1])
                                .crossfade(true) // Enables crossfade transition
                                .build(),
                            contentDescription = null, // replace with your content description,
                            alignment = Alignment.TopCenter,
                        )
                    }
                }
            }
        }
    }
}
