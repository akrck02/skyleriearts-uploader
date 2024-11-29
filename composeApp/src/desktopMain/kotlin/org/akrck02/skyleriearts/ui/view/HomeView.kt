package org.akrck02.skyleriearts.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.dragMessage
import kotlinproject.composeapp.generated.resources.hi
import kotlinproject.composeapp.generated.resources.update
import org.akrck02.skyleriearts.core.addFileToResources
import org.akrck02.skyleriearts.core.addImageFileToGallery
import org.akrck02.skyleriearts.model.ImageData
import org.akrck02.skyleriearts.navigation.GalleryRoute
import org.akrck02.skyleriearts.ui.drag.DragComposable
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeView(
    navController: NavHostController,
    gallery: SnapshotStateMap<String, ImageData>
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxHeight().fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            UploadSection(navController, gallery)
        }
    }

}


@Composable
private fun UploadSection(
    navController: NavHostController,
    gallery: SnapshotStateMap<String, ImageData>
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight()
    ) {
        Text(
            text = stringResource(Res.string.hi),
            color = MaterialTheme.colors.primary,
            fontSize = 2.em,
            modifier = Modifier.padding(20.dp)
        )

        DragComposable(
            text = stringResource(Res.string.dragMessage),
            onDrag = { path -> addFileToResources(path) },
            onFileAdded = addImageFileToGallery(gallery),
            onFinish = { navController.navigate(GalleryRoute) }
        )

    }
}

