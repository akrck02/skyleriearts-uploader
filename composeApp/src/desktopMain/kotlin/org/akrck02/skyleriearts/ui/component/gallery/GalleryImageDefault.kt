package org.akrck02.skyleriearts.ui.component.gallery

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import org.akrck02.skyleriearts.ui.theme.DEFAULT_ROUNDED_SHAPE
import org.akrck02.skyleriearts.ui.theme.TOTAL_ROUNDED_SHAPE


class GalleryImageDefault {


    companion object {


        /**
         * Get the default info chip modifier
         */
        val infoChipModifier = Modifier.size(50.dp).padding(10.dp)

        /**
         * Get the default selected brush
         */
        @Composable
        private fun selectedBrush() = Brush.sweepGradient(
            listOf(MaterialTheme.colors.primary, MaterialTheme.colors.primary)
        )

        /**
         * Get the default image card shape
         * @param round if the shape is round
         * @return The shape
         */
        fun imageShape(round: Boolean): RoundedCornerShape =
            if (round) TOTAL_ROUNDED_SHAPE else DEFAULT_ROUNDED_SHAPE

        /**
         * Get the default image card surface modifier
         * @param modifier The current modified
         * @param shape The current shape
         * @return The clipped modifier
         */
        fun surfaceModifier(
            modifier: Modifier,
            shape: RoundedCornerShape,
        ): Modifier {
            return modifier.clip(shape).clipToBounds()
        }


        /**
         * Get the default image modifier
         * @param round if the image card is round
         */
        @Composable
        fun imageModifier(round: Boolean, selected: Boolean): Modifier {

            val shape = imageShape(round)
            val imageModifier = Modifier.fillMaxSize().clip(shape)

//            if (selected) {
//                imageModifier = imageModifier.border(
//                    width = 8.dp,
//                    brush = selectedBrush(),
//                    shape = shape
//                )
//            }

            return imageModifier
        }

    }
}
