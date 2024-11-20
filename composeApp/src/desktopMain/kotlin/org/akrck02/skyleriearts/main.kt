package org.akrck02.skyleriearts

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import org.akrck02.skyleriearts.ui.theme.DEFAULT_WINDOW_HEIGHT
import org.akrck02.skyleriearts.ui.theme.DEFAULT_WINDOW_WIDTH

@OptIn(ExperimentalComposeUiApi::class)
fun main() = application {

    val state = WindowState(
        width = DEFAULT_WINDOW_WIDTH,
        height = DEFAULT_WINDOW_HEIGHT,
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "Skyleriearts - Upload your images.",
        state = state
    ) { App() }

}