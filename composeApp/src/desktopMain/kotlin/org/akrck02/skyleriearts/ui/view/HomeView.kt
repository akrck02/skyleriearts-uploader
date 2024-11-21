package org.akrck02.skyleriearts.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import org.akrck02.skyleriearts.core.addFileToResources
import org.akrck02.skyleriearts.core.getResourcesPath
import org.akrck02.skyleriearts.core.saveGalleryToFile
import org.akrck02.skyleriearts.model.ImageData
import org.akrck02.skyleriearts.navigation.ImageDetailRoute
import org.akrck02.skyleriearts.navigation.NavigationType
import org.akrck02.skyleriearts.ui.card.ImageCard
import org.akrck02.skyleriearts.ui.drag.DragComposable

@Composable
fun HomeView(
    navController: NavHostController,
    gallery: SnapshotStateMap<String, ImageData>,
    imagesToUpload: SnapshotStateList<ImageData>
) {

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        UploadSection(gallery, imagesToUpload)
        ImageList(navController, imagesToUpload)
    }
}

@Composable
private fun UploadSection(
    gallery: SnapshotStateMap<String, ImageData>,
    imagesToUpload: SnapshotStateList<ImageData>
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight()
    ) {
        Text(
            text = "Hi Skylerie!",
            color = MaterialTheme.colors.primary,
            fontSize = 2.em,
            modifier = Modifier.padding(20.dp)
        )

        DragComposable(
            text = "Drag your images here",
            onDrag = { path -> addFileToResources(path) },
            onFileAdded = { file ->

                // if data exists, get it
                var data = gallery[file.name]
                if (data == null) {
                    data = ImageData(
                        name = file.name,
                        path = getResourcesPath(file.name),
                    )

                    gallery[data.name] = data
                }

                // if it is a new image to upload, add it
                if (imagesToUpload.contains(data).not()) {
                    imagesToUpload.add(data)
                }

                saveGalleryToFile(gallery)

            }
        )

        Button(onClick = { println(gallery.size) }) {
            Text("Upload")
        }
    }

}

@Composable
private fun ImageList(navController: NavHostController, images: SnapshotStateList<ImageData>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = Modifier.padding(PaddingValues(40.dp, 0.dp, 40.dp)).fillMaxSize(.5f)
            .fillMaxHeight()
    ) {
        items(images, key = { it.name }) {
            ImageCard(
                imageData = it,
                modifier = Modifier.padding(5.dp)
                    .aspectRatio(1f)
                    .size(150.dp)
                    .clickable {
                        navController.navigate(
                            route = ImageDetailRoute(
                                item = NavigationType(
                                    imageData = it
                                )
                            )
                        )
                    }
            )
        }
    }
}
