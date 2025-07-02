package org.akrck02.skyleriearts.ui.view.upload

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import org.akrck02.skyleriearts.service.ImageManipulationService
import org.akrck02.skyleriearts.ui.component.drag.DragComposable
import org.akrck02.skyleriearts.viewmodel.AppViewModel
import org.jetbrains.compose.resources.stringResource
import skylerieartsuploader.composeapp.generated.resources.Res
import skylerieartsuploader.composeapp.generated.resources.dragMessage
import skylerieartsuploader.composeapp.generated.resources.hi
import java.io.File

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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UploadSection(appViewModel: AppViewModel) {

    var showImageAddView by remember { mutableStateOf(false) }
    var addedImageList = remember { mutableListOf<File>() }
    
    if (showImageAddView) {
        ImageAddView(addedImageList)
        //appViewModel.addFileToResources(path)
        return
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight()
    ) {
        Text(
            text = stringResource(Res.string.hi),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 2.em,
            modifier = Modifier.padding(20.dp)
        )

        DragComposable(
            text = stringResource(Res.string.dragMessage),
            onStarted = { if (addedImageList.isEmpty().not()) showImageAddView = true },
            onDrop = { path ->
                try {
                    ImageManipulationService.getImageFile(path).also { addedImageList.add(it) }
                } catch (e: Exception) {
                    null
                }
            },
            onFinish = {}
        )
    }
}

