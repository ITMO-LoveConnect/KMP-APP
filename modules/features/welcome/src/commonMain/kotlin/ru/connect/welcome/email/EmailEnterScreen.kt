package ru.connect.welcome.email

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import ru.connect.core.ui.buttons.ProgressButton
import ru.connect.core.ui.extensions.maxFullScreenWidth
import ru.connect.core.ui.snackbar.SnackBarComponent
import ru.connect.core.ui.theme.ConnectTheme

@Composable
internal fun EmailEnterScreen(
    onNavigateUp: () -> Unit,
    onOtpScreenNavigate: (String) -> Unit,
    viewModel: EmailEnterViewModel = koinViewModel(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val uiState = viewModel.state.collectAsState().value
    val ui = uiState.model
    viewModel.handleNavigation { target ->
        when (target) {
            EmailEnterNavigationTarget.Back -> onNavigateUp()
            is EmailEnterNavigationTarget.OtpScreenTarget -> onOtpScreenNavigate(target.isuNumber)
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
    ui: EmailEnterUi,
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
                        append("Пожалуйста, введите ваш табельный номер ИСУ. На адрес электронной почты ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("<Номер_ИСУ>@niuitmo.ru")
                        }
                        append(" будет отправлен код подтверждения для дальнейших действий.")
                    }
                )
            }
        }

        Spacer(modifier = Modifier.heightIn(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = ui.isuNumber,
            label = { Text("Номер ИСУ") },
            onValueChange = onIsuNumberChanged,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = ConnectTheme.colors.surface.surfaceContainer,
                disabledPlaceholderColor = Color.Black,
                unfocusedLabelColor = ConnectTheme.colors.caption,

                ),
            singleLine = true,
        )

        Spacer(modifier = Modifier.weight(1f))
        ProgressButton(
            text = "Отправить номер ИСУ",
            modifier = Modifier.fillMaxWidth(),
            onClick = onSendButtonClick,
            enabled = ui.isButtonEnabled,
            inProgress = ui.isButtonInProgress,
        )
    }
}
