package org.akrck02.skyleriearts.ui.view.image.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material.icons.rounded.Interests
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.addNew
import kotlinproject.composeapp.generated.resources.addTo
import kotlinproject.composeapp.generated.resources.categories
import kotlinproject.composeapp.generated.resources.description
import kotlinproject.composeapp.generated.resources.name
import kotlinproject.composeapp.generated.resources.noThingsHereAddOne
import kotlinproject.composeapp.generated.resources.notNow
import kotlinproject.composeapp.generated.resources.projects
import kotlinproject.composeapp.generated.resources.willOrderYourImagesWebsite
import org.akrck02.skyleriearts.core.addIfNotPresent
import org.akrck02.skyleriearts.core.deleteFromGallery
import org.akrck02.skyleriearts.core.removeIfPresent
import org.akrck02.skyleriearts.model.ImageData
import org.akrck02.skyleriearts.navigation.GalleryRoute
import org.akrck02.skyleriearts.navigation.ImageFullScreenRoute
import org.akrck02.skyleriearts.navigation.NavigationType
import org.akrck02.skyleriearts.navigation.navigateSecurely
import org.akrck02.skyleriearts.ui.control.ControlsBar
import org.akrck02.skyleriearts.ui.gallery.GalleryImage
import org.akrck02.skyleriearts.ui.input.IconButtonBasicData
import org.akrck02.skyleriearts.ui.input.MaterialTextField
import org.akrck02.skyleriearts.ui.modal.MaterialAlertInputDialog
import org.akrck02.skyleriearts.ui.tag.TagContainer
import org.jetbrains.compose.resources.stringResource
import java.util.Locale

/**
 * ImageDetailView
 */
@Composable
fun ImageDetailView(
    navController: NavHostController,
    data: NavigationType,
    gallery: SnapshotStateMap<String, ImageData>
) {

    val image by remember { mutableStateOf(gallery[data.imageData.name]) }
    println("Details for image $image")
    image?.let {
        Column(modifier = Modifier.fillMaxSize()) {
            ControlsBar(getButtonControls(gallery, navController, it))
            ImageDetailComponent(navController, it)
        }
    }
}

/**
 * Get button controls
 */
private fun getButtonControls(
    gallery: SnapshotStateMap<String, ImageData>,
    navController: NavHostController,
    imageData: ImageData
) = listOf(
    IconButtonBasicData(
        icon = Icons.Rounded.DeleteOutline,
        description = "Remove",

        onClick = {
            deleteFromGallery(imageData, gallery)

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
private fun ImageDetailComponent(navController: NavHostController, image: ImageData) {

    var showAlert by remember { mutableStateOf(false) }
    var addingType by remember { mutableStateOf(0) }

    Row(modifier = Modifier.fillMaxSize()) {
        ImageDetailForm(navController, image)
        Column(
            modifier = Modifier.padding(top = 20.dp).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {

            TagContainer(
                title = stringResource(Res.string.projects),
                tags = image.projects,
                icons = Icons.Rounded.Interests,
                emptyText = stringResource(
                    Res.string.noThingsHereAddOne,
                    stringResource(Res.string.projects).lowercase(Locale.getDefault())
                ),
                onAdd = {
                    addingType = 0
                    showAlert = true
                },
                onRemove = { project -> image.projects.removeIfPresent(project) }
            )

            TagContainer(
                title = stringResource(Res.string.categories),
                tags = image.tags,
                emptyText = stringResource(
                    Res.string.noThingsHereAddOne,
                    stringResource(Res.string.categories).lowercase(Locale.getDefault())
                ),
                onAdd = {
                    addingType = 1
                    showAlert = true
                },
                onRemove = { tag -> image.tags.removeIfPresent(tag) }
            )

        }
    }

    if (showAlert) {
        var tagName by remember { mutableStateOf("") }
        val tagType =
            if (addingType == 0) stringResource(Res.string.projects).lowercase(
                Locale.getDefault()
            )
            else stringResource(Res.string.categories).lowercase(
                Locale.getDefault()
            )

        MaterialAlertInputDialog(
            title = stringResource(Res.string.addTo, tagType, image.name),
            description = stringResource(Res.string.willOrderYourImagesWebsite, tagType),
            acceptText = stringResource(Res.string.addNew, tagType),
            cancelText = stringResource(Res.string.notNow),
            onClose = { showAlert = false },
            onClickAccept = {
                println("Adding project $tagName for image $image")
                image.projects.addIfNotPresent(tagName.lowercase(Locale.getDefault()))
            },
            onTextFieldValueChange = { tagName = it },
            textFieldLabel = tagType,
            textFieldValue = tagName
        )

    }
}

/**
 * Form to edit image details
 */
@Composable
private fun ImageDetailForm(navController: NavHostController, imageData: ImageData) {
    Column(
        modifier = Modifier.fillMaxHeight().fillMaxWidth(.5f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GalleryImage(
            data = imageData,
            modifier = Modifier.size(200.dp).padding(20.dp),
            round = true,
            onClick = {
                navController.navigateSecurely(
                    ImageFullScreenRoute(
                        NavigationType(imageData = imageData)
                    )
                )
            }
        )

        var imageName by remember { mutableStateOf(imageData.name) }
        val width = 350.dp

        MaterialTextField(
            value = imageName,
            onValueChange = {
                imageName = it
                imageData.name = it
            },
            label = stringResource(Res.string.name),
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
            label = stringResource(Res.string.description),
            width = width
        )
    }
}