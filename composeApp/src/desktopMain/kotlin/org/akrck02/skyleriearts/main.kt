package org.akrck02.skyleriearts

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Upload your images.",
    ) {
        App()
    }
}