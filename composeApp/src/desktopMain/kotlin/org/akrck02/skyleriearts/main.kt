package org.akrck02.skyleriearts

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

@OptIn(ExperimentalComposeUiApi::class)
fun main() = application {

    val state = WindowState(
        width = 1280.dp,
        height = 800.dp
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "Skyleriearts - Upload your images.",
        state = state
    ) {
        App()
    }
}