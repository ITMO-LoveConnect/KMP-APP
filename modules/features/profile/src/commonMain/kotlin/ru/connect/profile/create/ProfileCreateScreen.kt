package ru.connect.profile.create

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mohamedrejeb.calf.core.LocalPlatformContext
import com.mohamedrejeb.calf.io.readByteArray
import com.mohamedrejeb.calf.picker.FilePickerFileType
import com.mohamedrejeb.calf.picker.FilePickerSelectionMode
import com.mohamedrejeb.calf.picker.rememberFilePickerLauncher
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import ru.connect.core.extensions.onError
import ru.connect.core.extensions.onLoading
import ru.connect.core.extensions.onSuccess
import ru.connect.core.ui.buttons.ProgressButton
import ru.connect.core.ui.error.ErrorScreen
import ru.connect.core.ui.extensions.maxFullScreenWidth
import ru.connect.core.ui.loading.CircularProgressIndicatorLoadingScreen
import ru.connect.core.ui.snackbar.SnackBarComponent
import ru.connect.core.ui.theme.ConnectTheme
import ru.connect.domain.tag.models.TagCategory

@Composable
fun ProfileCreateScreen(
    onNavigateUp: () -> Unit,
    viewModel: ProfileCreateViewModel = koinViewModel(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val uiState = viewModel.state.collectAsState().value
    val ui = uiState.model
    viewModel.handleNavigation { target ->
        when (target) {
            ProfileCreateNavigationTarget.Back -> onNavigateUp()
        }
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
                        text = "Создание профиля"
                    )
                },
                backgroundColor = ConnectTheme.colors.material.background,
                navigationIcon = {
                    IconButton(onClick = viewModel::navigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                }
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
                    onFileSelect = viewModel::onFileSelect,
                    onDateSelect = viewModel::onDateSelect,
                    onRemove = viewModel::onRemove,
                    onTagClick = viewModel::onTagClick,
                    onClickButton = viewModel::onClickButton
                )
            }
        }

        ui.onLoading { CircularProgressIndicatorLoadingScreen() }

        ui.onError { ErrorScreen(onRefresh = viewModel::onRefresh) }

        SnackbarHost(snackbarHostState)
    }
}

@Suppress("LongMethod")
@Composable
private fun Content(
    ui: ProfileCreateUi,
    onFileSelect: (ByteArray) -> Unit,
    onDateSelect: (Long?) -> Unit,
    onRemove: (Int) -> Unit,
    onTagClick: (TagCategory, String) -> Unit,
    onClickButton: () -> Unit,
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(scrollState)
            .maxFullScreenWidth()
            .padding(horizontal = 16.dp)
            .padding(vertical = 24.dp),
    ) {
        OutlinedCard(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
            ) {
                TextGroup(
                    label = "Имя:",
                    text = ui.name,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                TextGroup(
                    label = "Пол:",
                    text = ui.gender,
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextGroup(
                    label = "Факультет:",
                    text = ui.faculty,
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextGroup(
                    label = "Курс:",
                    text = ui.course,
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = ui.birthday,
            onValueChange = { },
            label = { Text("Дата рождения") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = !showDatePicker }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )

        val scope = rememberCoroutineScope()
        val context = LocalPlatformContext.current

        val pickerLauncher = rememberFilePickerLauncher(
            type = FilePickerFileType.Image,
            selectionMode = FilePickerSelectionMode.Single,
            onResult = { files ->
                scope.launch {
                    files.firstOrNull()?.let { file ->
                        // Do something with the selected file
                        // You can get the ByteArray of the file
                        onFileSelect(file.readByteArray(context))
                    }
                }
            }
        )

        val pagerState = rememberPagerState { ui.images.size }
        val horizontalPadding = 120.dp // Padding for peeking effect
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = horizontalPadding),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
        ) { pos ->
            when (val cardUI = ui.images[pos]) {
                ProfileCreateUi.ImageCardUi.Add -> AddImageComponent(onClick = { pickerLauncher.launch() })
                is ProfileCreateUi.ImageCardUi.Image -> ImageComponent(image = cardUI.bitmap, onRemove = { onRemove(pos) })
            }
        }
        Spacer(modifier = Modifier.height(4.dp))

        Divider()
        Spacer(modifier = Modifier.height(4.dp))

        ui.groups.forEach { group ->
            TagGroup(group = group, onTagClick = { onTagClick(group.type, it) })
        }

        Spacer(modifier = Modifier.height(24.dp))

        ProgressButton(
            text = "Создать профиль",
            modifier = Modifier.fillMaxWidth(),
            enabled = ui.isButtonEnabled,
            inProgress = ui.isButtonInProgress,
            onClick = onClickButton,
        )

        if (showDatePicker) {
            DatePickerModalInput(
                onDateSelected = {
                    onDateSelect(it)
                    showDatePicker = false
                },
                onDismiss = {
                    showDatePicker = false
                }
            )
        }
    }
}

@Composable
private fun ImageComponent(
    image: ImageBitmap,
    onRemove: () -> Unit
) {
    Box(
        modifier = Modifier
            .widthIn(max = 120.dp)
            .height(200.dp)
            .padding(8.dp)
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                bitmap = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        IconButton(
            onClick = onRemove,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp)
                .size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Remove image",
                tint = Color.Red
            )
        }
    }
}

@Composable
private fun AddImageComponent(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .widthIn(max = 120.dp)
            .height(200.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize().clickable(onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = ConnectTheme.colors.material.primary
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModalInput(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Отменить")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Composable
fun TagGroup(group: ProfileCreateUi.GroupsTagUi, onTagClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = group.name,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(end = 8.dp)
                .align(Alignment.CenterVertically)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(group.tags.size) { index ->
                TagItem(
                    tag = group.tags[index],
                    onClick = { onTagClick(group.tags[index].id) }
                )
            }
        }
    }
}

@Composable
fun TagItem(tag: ProfileCreateUi.TagUi, onClick: () -> Unit) {
    FilterChip(selected = tag.isSelected, label = {
        Text(tag.name)
    }, onClick = onClick)
}

@Composable
private fun TextGroup(label: String, text: String) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = label, style = ConnectTheme.typography.material.subtitle2, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(2.dp))
        Text(modifier = Modifier.weight(1f), text = text, style = ConnectTheme.typography.material.body1, textAlign = TextAlign.End)
    }
}
