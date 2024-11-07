package ru.connect.welcome.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import itmo_loveconnect.modules.core_ui.generated.resources.Res
import itmo_loveconnect.modules.core_ui.generated.resources.itmo_love_connect_logo_big
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import ru.connect.core.ui.buttons.ProgressButton
import ru.connect.core.ui.extensions.maxFullScreenWidth
import ru.connect.core.ui.theme.ConnectTheme

@Composable
@Suppress("UnusedPrivateProperty")
fun WelcomeScreen(
    onLoginScreenNavigate: () -> Unit,
    viewModel: WelcomeViewModel = koinViewModel(),

    ) {

    val uiState = viewModel.state.collectAsState().value
    viewModel.handleNavigation { target ->
        when (target) {
            WelcomeNavigationTarget.LoginScreenDestination -> onLoginScreenNavigate()
        }
    }

    Scaffold {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter,
        ) {
            Content(
                onLoginButtonClick = viewModel::onLoginButtonClick,
            )
        }
    }
}

@Composable
private fun Content(
    onLoginButtonClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .maxFullScreenWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 24.dp),
    ) {

        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 40.dp)
                .align(Alignment.CenterHorizontally),
        ) {
            Column(
                modifier = Modifier.align(Alignment.TopCenter),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Image(
                    painter = painterResource(Res.drawable.itmo_love_connect_logo_big),
                    contentDescription = null
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Найди свою любовь",
                    textAlign = TextAlign.Center,
                    style = ConnectTheme.typography.material.body1,
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        ProgressButton(
            text = "Войти",
            modifier = Modifier.fillMaxWidth(),
            onClick = onLoginButtonClick,
        )
    }
}
