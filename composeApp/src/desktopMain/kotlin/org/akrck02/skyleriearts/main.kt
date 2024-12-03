package org.akrck02.skyleriearts

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.navigation.compose.rememberNavController
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.title
import org.akrck02.skyleriearts.core.getCurrentGalleryFromFile
import org.akrck02.skyleriearts.core.saveGalleryToFile
import org.akrck02.skyleriearts.model.ImageData
import org.akrck02.skyleriearts.navigation.NavigationType
import org.akrck02.skyleriearts.ui.theme.DEFAULT_WINDOW_HEIGHT
import org.akrck02.skyleriearts.ui.theme.DEFAULT_WINDOW_WIDTH
import org.akrck02.skyleriearts.ui.theme.getSystemThemeColors
import org.akrck02.skyleriearts.ui.view.image.detail.ImageDetailView
import org.jetbrains.compose.resources.stringResource
import java.util.Locale

fun main() = application {

    Locale.setDefault(Locale.forLanguageTag("es"))

    val state = WindowState(
        width = DEFAULT_WINDOW_WIDTH,
        height = DEFAULT_WINDOW_HEIGHT,
        placement = WindowPlacement.Floating
    )

    val gallery: SnapshotStateMap<String, ImageData> = getCurrentGalleryFromFile()

    Window(
        onCloseRequest = {
            exitApplication()
            saveGalleryToFile(gallery)
        },
        title = stringResource(Res.string.title),
        state = state,
        resizable = true
    ) {
        // TestWindow()
        App(gallery)
    }
}

@Composable
fun TestWindow() {

    val gallery: SnapshotStateMap<String, ImageData> = getCurrentGalleryFromFile()
    val navigationObject: NavigationType =
        NavigationType(imageData = gallery[gallery.keys.iterator().next()]!!)

    MaterialTheme(colors = getSystemThemeColors()) {
        ImageDetailView(
            navController = rememberNavController(),
            gallery = gallery,
            data = navigationObject.imageData,
        )
    }
}
