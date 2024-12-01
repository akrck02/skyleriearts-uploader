package org.akrck02.skyleriearts.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.dragMessage
import kotlinproject.composeapp.generated.resources.hi
import org.akrck02.skyleriearts.core.addFileToResources
import org.akrck02.skyleriearts.core.addImageFileToGallery
import org.akrck02.skyleriearts.model.ImageData
import org.akrck02.skyleriearts.navigation.GalleryRoute
import org.akrck02.skyleriearts.ui.drag.DragComposable
import org.jetbrains.compose.resources.stringResource

@Composable
fun UploadView(
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

    var showLoader by remember { mutableStateOf(false) }

    if (showLoader) {
        AlertDialog(
            title = { Text("Loading") },
            text = { Text("Images are being minified.") },
            buttons = {
            },
            onDismissRequest = { showLoader = false },
            modifier = Modifier.padding(10.dp),
            backgroundColor = MaterialTheme.colors.background
        )
        navController.navigate(GalleryRoute)
    }


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
            onStarted = { showLoader = true },
            onDrag = { path -> addFileToResources(path) },
            onFileAdded = addImageFileToGallery(gallery),
            onFinish = {}
        )

    }
}

