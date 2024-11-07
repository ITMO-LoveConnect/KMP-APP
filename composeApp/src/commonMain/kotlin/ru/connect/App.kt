package ru.connect

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import coil3.compose.setSingletonImageLoaderFactory
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import ru.connect.core.navigation.di.LocalNavController
import ru.connect.core.ui.theme.ConnectTheme

@Composable
@Preview
fun App() {
    ConnectTheme {
        setSingletonImageLoaderFactory { context ->
            getAsyncImageLoader(context)
        }
        CompositionLocalProvider(LocalNavController provides rememberNavController()) {
            val rootNavController = LocalNavController.current
            loadKoinModules(
                module { factory { rootNavController } }
            )

            Scaffold { innerPaddingModifier ->
                AppNavGraph(
                    navController = LocalNavController.current,
                    modifier = Modifier.padding(innerPaddingModifier)
                )
            }
        }
    }
}
