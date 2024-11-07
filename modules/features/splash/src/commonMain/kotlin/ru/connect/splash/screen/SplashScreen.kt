package ru.connect.splash.screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import itmo_loveconnect.modules.core_ui.generated.resources.Res
import itmo_loveconnect.modules.core_ui.generated.resources.itmo_love_connect_logo_big
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import ru.connect.core.navigation.FeatureNavigator
import ru.connect.core.navigation.PopupOptions
import ru.connect.main.common.MainGraphFeatureApi
import ru.connect.splash.common.SplashFeatureApi
import ru.connect.welcome.common.WelcomeFeatureApi

private const val ANIMATE_DELAY = 1_000L

@Composable
@Suppress("UnusedPrivateProperty")
fun SplashScreen(
    viewModel: SplashViewModel = koinViewModel(),
    featureNavigator: FeatureNavigator = koinInject(),
) {
    val uiState = viewModel.state.collectAsState().value

    viewModel.handleNavigation { target ->
        when (target) {
            SplashNavigationTarget.WelcomeScreen -> featureNavigator.navigate(
                WelcomeFeatureApi.Companion.WelcomeFeatureGraphDestination,
                popupOptions = PopupOptions(SplashFeatureApi.Companion.SplashFeatureTabDestination) {
                    inclusive = true
                }
            )
            SplashNavigationTarget.MainScreen -> featureNavigator.navigate(
                MainGraphFeatureApi.Companion.MainGraphFeatureDestination,
                popupOptions = PopupOptions(SplashFeatureApi.Companion.SplashFeatureTabDestination) {
                    inclusive = true
                }
            )
        }
    }

    Scaffold {
        Content()
    }
}

@Composable
private fun Content() {
    var isUp by remember { mutableStateOf(false) }

    val animatedPadding by animateDpAsState(
        targetValue = if (isUp) 0.dp else 40.dp,
        animationSpec = tween(durationMillis = ANIMATE_DELAY.toInt())
    )

    LaunchedEffect(Unit) {
        while (true) {
            isUp = !isUp
            delay(ANIMATE_DELAY)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = animatedPadding),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.padding(horizontal = 24.dp),
            painter = painterResource(Res.drawable.itmo_love_connect_logo_big),
            contentDescription = null
        )
    }
}
