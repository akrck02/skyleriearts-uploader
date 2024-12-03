package org.akrck02.skyleriearts.ui.view.gallery

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


class GalleryViewDefault {
    companion object {

        fun imageModifier(minSize: Dp) = Modifier.padding(
            top = 20.dp,
            start = 5.dp,
            end = 5.dp
        ).aspectRatio(1f).size(minSize)


        val lazyGridModifier = Modifier.padding(start = 60.dp, end = 60.dp)
            .fillMaxWidth()
            .fillMaxHeight()

    }
}