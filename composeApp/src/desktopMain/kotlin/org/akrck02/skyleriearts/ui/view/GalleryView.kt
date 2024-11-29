package org.akrck02.skyleriearts.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesomeMosaic
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.gallery
import kotlinproject.composeapp.generated.resources.numberOfImages
import org.akrck02.skyleriearts.model.ImageData
import org.akrck02.skyleriearts.navigation.ImageDetailRoute
import org.akrck02.skyleriearts.navigation.NavigationType
import org.akrck02.skyleriearts.navigation.navigateSecurely
import org.akrck02.skyleriearts.ui.card.ImageCard
import org.akrck02.skyleriearts.ui.theme.TOTAL_ROUNDED_SHAPE
import org.jetbrains.compose.resources.stringResource


@Composable
fun GalleryView(navController: NavHostController, gallery: SnapshotStateMap<String, ImageData>) {

    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            shape = TOTAL_ROUNDED_SHAPE,
            modifier = Modifier.fillMaxWidth().height(100.dp)
                .padding(top = 20.dp, start = 20.dp, end = 20.dp),
            color = Color(0xFFE7E5E1),
            contentColor = MaterialTheme.colors.primary,
        ) {
            Row {
                Icon(
                    imageVector = Icons.Outlined.AutoAwesomeMosaic,
                    contentDescription = stringResource(Res.string.gallery),
                    modifier = Modifier.padding(20.dp).size(50.dp)
                )

                Text(
                    text = stringResource(Res.string.numberOfImages, gallery.size),
                    modifier = Modifier.padding(top = 22.dp),
                    fontSize = 1.5.em
                )
            }

        }


        val minSize = 150.dp
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize),
            modifier = Modifier.padding(start = 60.dp, end = 60.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
        ) {

            val keys: MutableList<String> = mutableListOf()
            keys.addAll(gallery.keys)
            items(keys, key = { it }) {

                val image = gallery[it]!!
                ImageCard(
                    imageData = image,
                    modifier = Modifier.padding(
                        top = 20.dp,
                        start = 5.dp,
                        end = 5.dp
                    ).aspectRatio(1f)
                        .size(minSize),
                    onClick = {
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


}