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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
import org.akrck02.skyleriearts.core.deleteFromGallery
import org.akrck02.skyleriearts.model.ImageData
import org.akrck02.skyleriearts.model.TagType
import org.akrck02.skyleriearts.navigation.GalleryRoute
import org.akrck02.skyleriearts.navigation.ImageFullScreenRoute
import org.akrck02.skyleriearts.navigation.NavigationType
import org.akrck02.skyleriearts.navigation.navigateSecurely
import org.akrck02.skyleriearts.ui.component.control.ControlsBar
import org.akrck02.skyleriearts.ui.component.gallery.GalleryImage
import org.akrck02.skyleriearts.ui.component.input.IconButtonBasicData
import org.akrck02.skyleriearts.ui.component.input.MaterialTextField
import org.akrck02.skyleriearts.ui.component.modal.MaterialAlertInputDialog
import org.akrck02.skyleriearts.ui.component.tag.TagContainer
import org.jetbrains.compose.resources.stringResource
import java.util.Locale

/**
 * ImageDetailView
 */
@Composable
fun ImageDetailView(
    navController: NavHostController,
    data: ImageData,
    gallery: SnapshotStateMap<String, ImageData>
) {

    val viewModel = viewModel { ImageDetailViewModel(data) }
    
    val uiState by viewModel.uiState.collectAsState()
    println("Details for image $uiState")

    uiState.let {
        Column(modifier = Modifier.fillMaxSize()) {
            ControlsBar(getButtonControls(gallery, navController, it))
            ImageDetailComponent(
                image = it,
                onProjectAdd = viewModel::addProject,
                onProjectRemove = viewModel::removeProject,
                onCategoryAdd = viewModel::addCategory,
                onCategoryRemoved = viewModel::removeCategory,
                onNameValueChange = viewModel::setName,
                onDescriptionValueChange = viewModel::setDescription,
                onImageClick = {
                    navController.navigateSecurely(
                        ImageFullScreenRoute(NavigationType(imageData = it))
                    )
                }
            )
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
private fun ImageDetailComponent(
    image: ImageData,
    onProjectAdd: (String) -> Unit,
    onProjectRemove: (String) -> Unit,
    onCategoryAdd: (String) -> Unit,
    onCategoryRemoved: (String) -> Unit,
    onNameValueChange: (String) -> Unit,
    onDescriptionValueChange: (String) -> Unit,
    onImageClick: (ImageData) -> Unit
) {

    var showAlert by remember { mutableStateOf(false) }
    var tagType by remember { mutableStateOf(TagType.Project) }

    Row(modifier = Modifier.fillMaxSize()) {
        ImageDetailForm(
            imageData = image,
            onNameValueChange,
            onDescriptionValueChange,
            onImageClick
        )
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
                    tagType = TagType.Project
                    showAlert = true
                },
                onRemove = onProjectRemove
            )

            TagContainer(
                title = stringResource(Res.string.categories),
                tags = image.categories,
                emptyText = stringResource(
                    Res.string.noThingsHereAddOne,
                    stringResource(Res.string.categories).lowercase(Locale.getDefault())
                ),
                onAdd = {
                    tagType = TagType.Category
                    showAlert = true
                },
                onRemove = onCategoryRemoved
            )

        }
    }

    if (showAlert) {

        var tag by remember { mutableStateOf("") }
        val tagLocalName = when (tagType) {
            TagType.Project -> stringResource(Res.string.projects).lowercase(Locale.getDefault())
            TagType.Category -> stringResource(Res.string.categories).lowercase(Locale.getDefault())
        }

        MaterialAlertInputDialog(
            title = stringResource(Res.string.addTo, tagLocalName, image.name),
            description = stringResource(Res.string.willOrderYourImagesWebsite, tagLocalName),
            acceptText = stringResource(Res.string.addNew, tagLocalName),
            cancelText = stringResource(Res.string.notNow),
            onClose = { showAlert = false },
            onClickAccept = {
                println("Adding project $tag for image $image")
                if (TagType.Project == tagType) {
                    onProjectAdd(tag)
                } else {
                    onCategoryAdd(tag)
                }
            },
            onTextFieldValueChange = { tag = it },
            textFieldLabel = tagLocalName,
            textFieldValue = tag
        )

    }
}

/**
 * Form to edit image details
 */
@Composable
private fun ImageDetailForm(
    imageData: ImageData,
    onNameValueChange: (String) -> Unit,
    onDescriptionValueChange: (String) -> Unit,
    onImageClick: (ImageData) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxHeight().fillMaxWidth(.5f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GalleryImage(
            data = imageData,
            modifier = Modifier.size(200.dp).padding(20.dp),
            round = true,
            onClick = { onImageClick(imageData) }
        )

        val width = 350.dp

        MaterialTextField(
            value = imageData.name,
            onValueChange = onNameValueChange,
            label = stringResource(Res.string.name),
            enabled = false,
            width = width
        )


        MaterialTextField(
            value = imageData.description,
            onValueChange = onDescriptionValueChange,
            label = stringResource(Res.string.description),
            width = width
        )
    }
}