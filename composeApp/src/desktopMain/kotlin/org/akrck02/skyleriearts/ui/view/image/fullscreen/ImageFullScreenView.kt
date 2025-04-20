package org.akrck02.skyleriearts.ui.view.image.fullscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import org.akrck02.skyleriearts.core.processor.FileProcessor
import org.akrck02.skyleriearts.navigation.NavigationType
import org.akrck02.skyleriearts.viewmodel.AppViewModel
import java.nio.file.Files
import kotlin.io.path.Path


@Composable
fun ImageFullScreenView(
    appViewModel: AppViewModel,
    data: NavigationType
) {

    val imageData = data.imageData

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            color = MaterialTheme.colors.primary,
            text = imageData.name,
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            textAlign = TextAlign.Center,
            fontSize = 1.5.em
        )

        if (Files.exists(Path(imageData.path)).not())
            return

        Surface(
            color = Color.Transparent,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier.fillMaxSize().padding(20.dp),
                bitmap = FileProcessor.loadImageFrom(imageData.path),
                contentDescription = imageData.name,
                contentScale = ContentScale.Inside
            )
        }
    }


}