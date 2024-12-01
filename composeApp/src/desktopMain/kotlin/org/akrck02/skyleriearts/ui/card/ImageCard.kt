package org.akrck02.skyleriearts.ui.card


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Grade
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
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
    selectedName: String = "Nuevo",
    onClick: () -> Unit = {}
) {

    val shape = if (round) TOTAL_ROUNDED_SHAPE else DEFAULT_ROUNDED_SHAPE
    Column(
        verticalArrangement = Arrangement.Bottom
    ) {


        Box {
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

            if (imageData.new) {

//                val infiniteTransition = rememberInfiniteTransition(label = "infinite")
//
//                val horizontal by infiniteTransition.animateValue(
//                    initialValue = 90.dp,
//                    targetValue = 86.dp,
//                    animationSpec = infiniteRepeatable(
//                        animation = tween(250, easing = LinearEasing),
//                        repeatMode = RepeatMode.Reverse
//                    ),
//                    typeConverter = Dp.VectorConverter
//                )

                Surface(
                    shape = TOTAL_ROUNDED_SHAPE,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.size(50.dp).padding(10.dp),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Grade,
                        contentDescription = "New"
                    )
                }
            }

        }


    }


}