package org.akrck02.skyleriearts.ui.view.upload

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import org.akrck02.skyleriearts.navigation.ProjectsRoute
import org.akrck02.skyleriearts.ui.component.drag.DragComposable
import org.akrck02.skyleriearts.viewmodel.AppViewModel
import org.jetbrains.compose.resources.stringResource
import skylerieartsuploader.composeapp.generated.resources.Res
import skylerieartsuploader.composeapp.generated.resources.dragMessage
import skylerieartsuploader.composeapp.generated.resources.hi

@Composable
fun UploadView(appViewModel: AppViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxHeight().fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) { UploadSection(appViewModel) }
    }
}


@Composable
private fun UploadSection(appViewModel: AppViewModel) {

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

        appViewModel.navigate(ProjectsRoute)
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
            onDrag = { path -> appViewModel.addFileToResources(path) },
            onFileAdded = { file -> appViewModel.addImageFileToGallery(file) },
            onFinish = {}
        )
    }
}

