package org.akrck02.skyleriearts.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import org.akrck02.skyleriearts.core.addFileToResources
import org.akrck02.skyleriearts.core.getResourcesPath
import org.akrck02.skyleriearts.model.ImageData
import org.akrck02.skyleriearts.ui.card.ImageCard
import org.akrck02.skyleriearts.ui.drag.DragComposable
import org.akrck02.skyleriearts.ui.theme.getSystemThemeColors

@Composable
fun MainView() {
    MaterialTheme(
        colors = getSystemThemeColors()
    ) {
        val systemColors = getSystemThemeColors()
        val images: SnapshotStateList<ImageData> = remember { SnapshotStateList() }
        LaunchedEffect(images) { println(images) }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            UploadSection(systemColors, images)
            ImageList(images)
        }
    }

}

@Composable
private fun UploadSection(
    systemColors: Colors,
    images: SnapshotStateList<ImageData>
) {
    Text(
        text = "Hi Skylerie!",
        color = systemColors.primary,
        fontSize = 2.em,
        modifier = Modifier.padding(20.dp)
    )

    DragComposable(
        text = "Drag your images here",
        onDrag = { path -> addFileToResources(path) },
        onFileAdded = { file ->
            val data = ImageData(
                name = file.name,
                path = getResourcesPath(file.name)
            )

            if (images.contains(data).not()) {
                images.addLast(data)
            }
        }
    )

    Button(onClick = { println(images.size) }) {
        Text("Upload")
    }
}

@Composable
private fun ImageList(images: SnapshotStateList<ImageData>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(220.dp),
        modifier = Modifier.padding(40.dp)
    ) {
        items(images, key = { it.name }) {
            ImageCard(
                imageData = it,
                modifier = Modifier.padding(5.dp)
                    .size(200.dp)
                    .clickable {
                        // Text(it.name)
                    }
            )
        }
    }
}
