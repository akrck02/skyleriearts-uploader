package org.akrck02.skyleriearts.ui.card


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import org.akrck02.skyleriearts.model.ImageData
import org.akrck02.skyleriearts.ui.theme.DEFAULT_ROUNDED_SHAPE
import org.akrck02.skyleriearts.ui.theme.TOTAL_ROUNDED_SHAPE
import org.akrck02.skyleriearts.util.loadImageFrom


@Composable
fun ImageCard(imageData: ImageData, modifier: Modifier, round: Boolean = false) {


    Surface(
        shape = if (round) TOTAL_ROUNDED_SHAPE else DEFAULT_ROUNDED_SHAPE,
        modifier = modifier,
        color = Color.Transparent
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            bitmap = loadImageFrom(imageData.path),
            contentDescription = imageData.name,
            contentScale = ContentScale.Crop
        )
    }
}