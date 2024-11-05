package ru.connect.feature1.navigation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import ru.connect.core.extensions.onError
import ru.connect.core.extensions.onLoading
import ru.connect.core.extensions.onSuccess

@Composable
internal fun Feature1Screen(
    onNavigateUp: () -> Unit,
    viewModel: Feature1ViewModel = koinViewModel<Feature1ViewModel>(),
) {

    val uiState = viewModel.state.collectAsState().value
    val ui = uiState.model

    viewModel.handleNavigation { target ->
        when (target) {
            Feature1NavigationTarget.Back -> onNavigateUp()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Feature 1") },
                navigationIcon = {
                    IconButton(onClick = viewModel::onNavigateUp) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        ui.onSuccess {
            Content(
                ui = data,
                onRefreshButtonClick = viewModel::onRefreshButtonClick,
            )
        }

        ui.onLoading {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        ui.onError {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error screen :(")
            }
        }
    }
}

@Composable
private fun Content(
    ui: Feature1Ui,
    onRefreshButtonClick: () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "This is screen Feature 1")
            Text(text = ui.resultText)
            Button(onClick = onRefreshButtonClick) {
                Text(text = "Refresh")
            }
        }
    }
}
