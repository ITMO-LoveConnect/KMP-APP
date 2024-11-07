package ru.connect.welcome.otp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import ru.connect.core.navigation.FeatureNavigator
import ru.connect.core.navigation.PopupOptions
import ru.connect.core.ui.buttons.ProgressButton
import ru.connect.core.ui.extensions.maxFullScreenWidth
import ru.connect.core.ui.snackbar.SnackBarComponent
import ru.connect.core.ui.theme.ConnectTheme
import ru.connect.main.common.MainGraphFeatureApi
import ru.connect.profile.common.ProfileFeatureApi
import ru.connect.welcome.screen.WelcomeFeatureScreenDestination

@Composable
internal fun OtpEnterScreen(
    onNavigateUp: () -> Unit,
    viewModel: OtpEnterViewModel = koinViewModel(),
    featureNavigator: FeatureNavigator = koinInject(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val uiState = viewModel.state.collectAsState().value
    val ui = uiState.model
    viewModel.handleNavigation { target ->
        when (target) {
            OtpEnterNavigationTarget.Back -> onNavigateUp()
            OtpEnterNavigationTarget.CreateProfile -> featureNavigator.navigate(
                destination = ProfileFeatureApi.Companion.ProfileFeatureGraphDestination,
                popupOptions = PopupOptions(
                    popupToRoute = WelcomeFeatureScreenDestination,
                    options = {
                        inclusive = false
                    },
                )
            )
            OtpEnterNavigationTarget.MainScreen -> featureNavigator.navigate(
                destination = MainGraphFeatureApi.Companion.MainGraphFeatureDestination,
                popupOptions = PopupOptions(
                    popupToRoute = WelcomeFeatureScreenDestination,
                    options = {
                        inclusive = true
                    },
                )
            )
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
                        text = "Вход в приложение"
                    )
                },
                backgroundColor = ConnectTheme.colors.material.background,
                navigationIcon = {
                    IconButton(onClick = viewModel::onNavigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter,
        ) {
            Content(
                ui = ui,
                onSendButtonClick = viewModel::onSendButtonClick,
                onIsuNumberChanged = viewModel::onIsuNumberEdit,
            )

            SnackbarHost(snackbarHostState)
        }
    }
}

@Composable
private fun Content(
    ui: OtpEnterUi,
    onSendButtonClick: () -> Unit,
    onIsuNumberChanged: (String) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .maxFullScreenWidth()
            .padding(horizontal = 16.dp)
            .padding(vertical = 24.dp),
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Пожалуйста, введите код подтверждения, который придет к вам на почту ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(ui.email)
                        }
                        append(
                            ". \nЕсли письма нет во входящих, то рекомендуем проверить СПАМ или " +
                                    "попробовать войти позже. \nЛюбовь подождет ❤\uFE0F"
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.heightIn(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = ui.otpCode,
            label = { Text("Код подтверждения") },
            onValueChange = onIsuNumberChanged,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = ConnectTheme.colors.surface.surfaceContainer,
                disabledPlaceholderColor = Color.Black,
                unfocusedLabelColor = ConnectTheme.colors.caption,
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
            )
        )

        Spacer(modifier = Modifier.weight(1f))
        ProgressButton(
            text = "Войти",
            modifier = Modifier.fillMaxWidth(),
            onClick = onSendButtonClick,
            enabled = ui.isButtonEnabled,
            inProgress = ui.isButtonInProgress,
        )
    }
}
