package com.akshayashokcode.bird_app_compose_kmm

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import bird_app_compose_kmm.composeapp.generated.resources.Res
import bird_app_compose_kmm.composeapp.generated.resources.compose_multiplatform
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        LaunchedEffect(Unit){

        }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    KamelImage(
                        asyncPainterResource("https://sebi.io/demo-image-api/pigeon/vladislav-nikonov-yVYaUSwkTOs-unsplash.jpg"),
                        contentDescription = "Pigeon",
                    )
                    Text("Compose: $greeting")
                }
            }
        }
    }
}