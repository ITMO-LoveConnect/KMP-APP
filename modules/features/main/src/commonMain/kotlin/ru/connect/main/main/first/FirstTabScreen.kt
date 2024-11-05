package ru.connect.main.main.first

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import itmo_loveconnect.modules.features.main.generated.resources.Res
import itmo_loveconnect.modules.features.main.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import ru.connect.core.navigation.FeatureNavigator
import ru.connect.feature1.common.Feature1Api

@Composable
internal fun FirstTabScreen(
    featureNavigator: FeatureNavigator = koinInject()
) {
    var showContent by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { showContent = !showContent }) {
            Text("Click me!")
        }

        AnimatedVisibility(showContent) {
            val greeting = remember { "FIRST" }
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(Res.drawable.compose_multiplatform), contentDescription = null)
                Text("Compose: $greeting")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { featureNavigator.navigate(Feature1Api.feature1Route) }) {
            Text("To feature 1")
        }
    }
}
