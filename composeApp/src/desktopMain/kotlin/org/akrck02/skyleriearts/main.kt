package org.akrck02.skyleriearts

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import org.akrck02.skyleriearts.module.initKoin
import org.akrck02.skyleriearts.ui.theme.DEFAULT_WINDOW_HEIGHT
import org.akrck02.skyleriearts.ui.theme.DEFAULT_WINDOW_WIDTH
import org.jetbrains.compose.resources.stringResource
import skylerieartsuploader.composeapp.generated.resources.Res
import skylerieartsuploader.composeapp.generated.resources.title
import java.util.Locale


fun main() = application {

    initKoin()
    Locale.setDefault(Locale.forLanguageTag("es"))

    val state = WindowState(
        width = DEFAULT_WINDOW_WIDTH,
        height = DEFAULT_WINDOW_HEIGHT,
        placement = WindowPlacement.Floating
    )

    Window(
        onCloseRequest = { exitApplication() },
        title = stringResource(Res.string.title),
        state = state,
        resizable = true
    ) { App() }
}
