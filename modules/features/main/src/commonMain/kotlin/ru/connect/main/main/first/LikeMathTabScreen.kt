package ru.connect.main.main.first

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
import ru.connect.core.ui.snackbar.SnackBarComponent
import ru.connect.core.ui.swipeable.SwipeCard
import ru.connect.core.ui.theme.ConnectShapes
import ru.connect.core.ui.theme.ConnectTheme

@Composable
internal fun LikeMathTabScreen(
    viewModel: LikeMatchViewModel = koinViewModel(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val uiState = viewModel.state.collectAsState().value
    val ui = uiState.model
    viewModel.handleNavigation { target ->
    }

    SnackBarComponent(
        snackbarHostState = snackbarHostState,
        error = uiState.alertError,
        onDismiss = viewModel::handleErrorAlertClose,
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "ITMO LoveConnect"
                    )
                },
                backgroundColor = ConnectTheme.colors.material.background,
            )
        }
    ) {

        ui.onSuccess {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter,
            ) {
                Content(
                    ui = data,
                    onLike = viewModel::onLike,
                    onDisLike = viewModel::onDisLike,
                )
            }
        }

        ui.onLoading { CircularProgressIndicatorLoadingScreen() }

        ui.onError {
            ErrorScreen(onRefresh = viewModel::onRefresh)
        }

        SnackbarHost(snackbarHostState)
    }
}

@Composable
private fun Content(
    ui: LikeMatchUi,
    onLike: (String) -> Unit,
    onDisLike: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .maxFullScreenWidth()
            .navigationBarsPadding()
            .padding(horizontal = 16.dp)
            .padding(bottom = 24.dp, top = 12.dp),
    ) {

        ui.profile.forEach { profile ->
            SwipeCard(
                swipeThreshold = 400f,
                onSwipeLeft = { onDisLike(profile.id) },
                onSwipeRight = { onLike(profile.id) },
            ) {
                ProfileCard(
                    ui = profile,
                    onLike = { onLike(profile.id) },
                    onDisLike = { onDisLike(profile.id) },
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ProfileCard(
    ui: LikeMatchUi.ProfileUi,
    onLike: () -> Unit,
    onDisLike: () -> Unit,
) {
    val context = LocalPlatformContext.current
    val horizontalState = rememberPagerState { ui.images.size }
    val bottomSheetState: BottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState,
    )
    val sheetPeekHeight = remember {
        240.dp
    }
    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize().padding(bottom = 60.dp),
        scaffoldState = scaffoldState,
        sheetPeekHeight = sheetPeekHeight,
        sheetBackgroundColor = Color(0xFFCFD8DC),
        sheetShape = ConnectShapes.RoundedCornerShape24,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column {
                    BottomSheetDialogHandle()

                    // Main scrollable content
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f, fill = false)
                            .padding(16.dp),
                    ) {
                        item {
                            Text(
                                text = ui.name, style = ConnectTheme.typography.material.h5,
                                fontWeight = FontWeight.Bold,
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = ui.facultet, style = ConnectTheme.typography.material.body1)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = ui.aboutMe, style = ConnectTheme.typography.material.body2,
                                fontWeight = FontWeight.ExtraLight
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            FlowRow(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                ui.tags.forEach {
                                    ElevatedAssistChip(
                                        onClick = {}, label = { Text(text = it.name) })
                                }
                            }
                            ui.tags.forEach {
                            }
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), // Offset to bring it up a bit,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        IconButton(
                            onClick = onDisLike,
                            modifier = Modifier
                                .size(60.dp)
                                .background(color = Color.Red, shape = CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close, // Replace with actual dislike icon resource
                                contentDescription = "Dislike",
                                tint = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.widthIn(min = 120.dp))

                        // Like button
                        IconButton(
                            onClick = onLike,
                            modifier = Modifier
                                .size(60.dp)
                                .background(color = Color(0xFF8E24AA), shape = CircleShape) // Purple color for like
                        ) {
                            Icon(
                                imageVector = Icons.Default.Favorite, // Replace with actual like icon resource
                                contentDescription = "Like",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .clip(ConnectShapes.RoundedCornerShape12)
                .background(ConnectTheme.colors.surface.surfaceContainerLow),
            contentAlignment = Alignment.TopCenter,
        ) {
            Row(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(horizontalState.pageCount) { iteration ->
                    val color = if (horizontalState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(8.dp)
                    )
                }
            }
            HorizontalPager(
                modifier = Modifier.fillMaxSize().padding(bottom = 120.dp).align(Alignment.TopCenter),
                state = horizontalState,
                verticalAlignment = Alignment.Top,
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 24.dp)
                        .clip(ConnectShapes.RoundedCornerShape12),
                    model = ImageRequest.Builder(context)
                        .data(ui.images[it])
                        .crossfade(true) // Enables crossfade transition
                        .build(),
                    contentDescription = null, // replace with your content description,
                    alignment = Alignment.TopCenter,
                )
            }
        }
    }
}

@Composable
fun BottomSheetDialogHandle() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .padding(top = 8.dp)
                .size(width = 32.dp, height = 4.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(Color.Black)
                .align(Alignment.Center)
        )
    }
}

class OffsetWrapper(var offset: Float = 0f)
