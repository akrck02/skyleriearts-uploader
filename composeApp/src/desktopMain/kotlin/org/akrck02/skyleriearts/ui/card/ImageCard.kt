package org.akrck02.skyleriearts.ui.card


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import org.akrck02.skyleriearts.core.loadImageFrom
import org.akrck02.skyleriearts.model.ImageData
import org.akrck02.skyleriearts.ui.theme.DEFAULT_ROUNDED_SHAPE
import org.akrck02.skyleriearts.ui.theme.TOTAL_ROUNDED_SHAPE
import java.nio.file.Files
import kotlin.io.path.Path


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImageCard(
    imageData: ImageData,
    modifier: Modifier,
    round: Boolean = false,
    onClick: () -> Unit = {}

) {

    val shape = if (round) TOTAL_ROUNDED_SHAPE else DEFAULT_ROUNDED_SHAPE
    Surface(
        shape = shape,
        modifier = modifier.clip(shape).clipToBounds(),
        color = Color.Transparent,
        onClick = onClick
    ) {

        if (Files.exists(Path(imageData.minPath))) {
            Image(
                modifier = Modifier.fillMaxSize(),
                bitmap = loadImageFrom(imageData.minPath),
                contentDescription = imageData.name,
                contentScale = ContentScale.Crop
            )

        }


    }
}