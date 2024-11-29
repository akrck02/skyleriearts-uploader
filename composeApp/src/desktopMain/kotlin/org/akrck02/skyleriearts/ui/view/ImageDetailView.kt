package org.akrck02.skyleriearts.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Interests
import androidx.compose.material.icons.rounded.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.akrck02.skyleriearts.core.removeFile
import org.akrck02.skyleriearts.core.removeResourceFile
import org.akrck02.skyleriearts.core.saveGalleryToFile
import org.akrck02.skyleriearts.model.ImageData
import org.akrck02.skyleriearts.navigation.GalleryRoute
import org.akrck02.skyleriearts.navigation.NavigationType
import org.akrck02.skyleriearts.navigation.navigateSecurely
import org.akrck02.skyleriearts.ui.card.ImageCard
import org.akrck02.skyleriearts.ui.control.ControlsBar
import org.akrck02.skyleriearts.ui.input.IconButtonBasicData
import org.akrck02.skyleriearts.ui.input.MaterialTextField
import org.akrck02.skyleriearts.ui.tag.TagContainer
import org.akrck02.skyleriearts.ui.theme.DEFAULT_ROUNDED_SHAPE
import org.akrck02.skyleriearts.ui.theme.MIN_ROUNDED_SHAPE

/**
 * ImageDetailView
 */
@Composable
fun ImageDetailView(
    navController: NavHostController,
    data: NavigationType,
    gallery: SnapshotStateMap<String, ImageData>
) {
    val imageData = gallery[data.imageData.name] ?: data.imageData
    Column(modifier = Modifier.fillMaxSize()) {
        ControlsBar(getButtonControls(gallery, data, navController, imageData))
        ImageDetailComponent(data, imageData)
    }
}

/**
 * Get button controls
 */
private fun getButtonControls(
    gallery: SnapshotStateMap<String, ImageData>,
    data: NavigationType,
    navController: NavHostController,
    imageData: ImageData
) = listOf(
    IconButtonBasicData(
        icon = Icons.Rounded.Delete,
        description = "Remove",

        onClick = {

            // remove the resources
            removeFile(data.imageData.path)
            removeFile(data.imageData.minPath)

            // remove the data
            gallery.remove(data.imageData.name)
            saveGalleryToFile(gallery)

            // navigate to gallery
            navController.navigateSecurely(GalleryRoute)
        },
    ),
    IconButtonBasicData(
        icon = Icons.Rounded.Close,
        description = "Close",
        onClick = { navController.navigateSecurely(GalleryRoute) }
    )
)

/**
 * Image detail component
 */
@Composable
private fun ImageDetailComponent(
    data: NavigationType,
    imageData: ImageData
) {

    var showAlert by remember { mutableStateOf(false) }

    Row(modifier = Modifier.fillMaxSize()) {
        ImageDetailForm(data, imageData)
        Column(
            modifier = Modifier.padding(top = 20.dp).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {

            val projectList = mutableListOf<String>()
            projectList.addAll(imageData.projects)

            val projects by remember { mutableStateOf(projectList) }
            TagContainer(
                title = "Projects",
                tags = projects,
                icons = Icons.Rounded.Interests,
                emptyText = "There are not projects here, feel free to add one.",
                onAdd = { showAlert = true }
            )

            val tagList = mutableListOf<String>()
            tagList.addAll(imageData.tags)

            val tags by remember { mutableStateOf(tagList) }
            TagContainer(
                title = "Tags",
                tags = tags,
                emptyText = "There are not tags here, feel free to add one.",
                onAdd = { showAlert = true }
            )

        }
    }

    if (showAlert) {
        AlertDialog(
            title = { Text("Add tags to ${imageData.name}") },
            text = { Text("The tags will order your images in the website.") },
            buttons = {

                Column(
                    modifier = Modifier.padding(10.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var tagName by remember { mutableStateOf("New tag") }
                    MaterialTextField(
                        value = tagName,
                        onValueChange = { tagName = it },
                        label = "Tag",
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Row(
                    modifier = Modifier.padding(10.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { showAlert = false },
                        modifier = Modifier.padding(end = 10.dp)
                    ) {
                        Text("Add")
                    }

                    Button(onClick = { showAlert = false }) {
                        Text("Cancel")
                    }
                }


            },
            onDismissRequest = {},
            modifier = Modifier.padding(10.dp)
        )
    }

}

/**
 * Form to edit image details
 */
@Composable
private fun ImageDetailForm(
    data: NavigationType,
    imageData: ImageData,
) {
    Column(
        modifier = Modifier.fillMaxHeight().fillMaxWidth(.5f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageCard(
            imageData = data.imageData,
            round = true,
            modifier = Modifier.size(200.dp).padding(20.dp),
        )

        var imageName by remember { mutableStateOf(imageData.name) }
        val width = 350.dp
1
        MaterialTextField(
            value = imageName,
            onValueChange = {
                imageName = it
                imageData.name = it
            },
            label = "Name",
            enabled = false,
            width = width
        )

        var imageDescription by remember { mutableStateOf(imageData.description) }
        MaterialTextField(
            value = imageDescription,
            onValueChange = {
                imageDescription = it
                imageData.description = it
            },
            label = "Description",
            width = width
        )
    }
}