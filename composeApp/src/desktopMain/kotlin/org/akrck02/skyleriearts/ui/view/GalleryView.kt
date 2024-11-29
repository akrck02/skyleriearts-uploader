package org.akrck02.skyleriearts.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.akrck02.skyleriearts.model.ImageData
import org.akrck02.skyleriearts.navigation.ImageDetailRoute
import org.akrck02.skyleriearts.navigation.NavigationType
import org.akrck02.skyleriearts.navigation.navigateSecurely
import org.akrck02.skyleriearts.ui.card.ImageCard


@Composable
fun GalleryView(navController: NavHostController, gallery: SnapshotStateMap<String, ImageData>) {
    val minSize = 150.dp
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize),
        modifier = Modifier.padding(start = 40.dp, end = 40.dp)
            .fillMaxWidth(.5f)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
    ) {

        val keys: MutableList<String> = mutableListOf()
        items(keys, key = { it }) {

            val image = gallery[it]!!
            ImageCard(
                imageData = image,
                modifier = Modifier.padding(5.dp)
                    .aspectRatio(1f)
                    .size(minSize)
                    .clickable {
                        navController.navigateSecurely(
                            route = ImageDetailRoute(
                                item = NavigationType(
                                    imageData = image
                                )
                            )
                        )
                    }
            )
        }
    }

}