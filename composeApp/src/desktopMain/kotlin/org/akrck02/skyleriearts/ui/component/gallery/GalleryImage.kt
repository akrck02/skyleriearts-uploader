package org.akrck02.skyleriearts.ui.component.gallery


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Grade
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.akrck02.skyleriearts.core.loadImageFrom
import org.akrck02.skyleriearts.model.ImageData
import org.akrck02.skyleriearts.ui.theme.TOTAL_ROUNDED_SHAPE
import java.nio.file.Files
import kotlin.io.path.Path


/**
 * Image to show in the gallery
 *
 * @param data The image data,
 * @param modifier The image modifier
 * @param showInfoChip If the info chip needs to be rendered
 * @param infoChipLabel The label of the info chip
 * @param round If the image container is round
 * @param selected If the image container is selected
 * @param onClick Callback for click action
 */
@Composable
fun GalleryImage(
    data: ImageData,
    modifier: Modifier = Modifier,
    showInfoChip: Boolean = false,
    infoChipLabel: String = "New",
    round: Boolean = false,
    selected: Boolean = false,
    grayscale: Boolean = false,
    onClick: () -> Unit = {}
) {
    val shape = GalleryImageDefault.imageShape(round)

    Column(verticalArrangement = Arrangement.Bottom) {
        Box {
            ImageSurface(
                data = data,
                shape = shape,
                modifier = modifier,
                onClick = onClick,
                round = round,
                selected = selected,
                grayscale = grayscale
            )

            if (showInfoChip) {
                ImageInfoChip(infoChipLabel)
            }

            if (selected) {
                CheckSign()
            }
        }
    }
}


@Composable
fun CheckSign() {
    Surface(
        color = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.surface,
        shape = CircleShape,
        modifier = Modifier.padding(top = 20.dp)
    ) {
        Icon(
            imageVector = Icons.Rounded.Check,
            "selected",
            modifier = Modifier.size(30.dp)
        )
    }
}

/**
 * The chip to show if needed
 * @param label The label for the info chip
 */
@Composable
private fun ImageInfoChip(label: String) {
    Surface(
        shape = TOTAL_ROUNDED_SHAPE,
        color = MaterialTheme.colors.primary,
        modifier = GalleryImageDefault.infoChipModifier,
    ) {
        Icon(
            imageVector = Icons.Rounded.Grade,
            contentDescription = label
        )
    }
}


/**
 * The image with a surface to click
 *
 * @param data The image data
 * @param shape The shape of the image container
 * @param modifier The modifier of the image container
 * @param round if the image container is round
 * @param selected if the image container is selected
 * @param onClick Callback for click action
 */
@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun ImageSurface(
    data: ImageData,
    shape: RoundedCornerShape,
    modifier: Modifier,
    round: Boolean,
    selected: Boolean,
    onClick: () -> Unit,
    grayscale: Boolean
) {

    if (Files.exists(Path(data.minPath)).not())
        return

    Surface(
        shape = shape,
        modifier = GalleryImageDefault.surfaceModifier(modifier, shape),
        color = Color.Transparent,
        onClick = onClick,
    ) {
        Image(
            modifier = GalleryImageDefault.imageModifier(round, selected),
            bitmap = loadImageFrom(data.minPath),
            contentDescription = data.name,
            contentScale = ContentScale.Crop,
            colorFilter = if (grayscale)
                ColorFilter.colorMatrix(ColorMatrix().apply {
                    setToSaturation(0f)
                }) else null
        )
    }

}

