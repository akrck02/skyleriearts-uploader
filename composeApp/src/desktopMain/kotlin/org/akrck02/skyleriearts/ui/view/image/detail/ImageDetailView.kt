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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.akrck02.skyleriearts.constant.ImageFullScreenRoute
import org.akrck02.skyleriearts.constant.ProjectsRoute
import org.akrck02.skyleriearts.ui.component.control.ControlsBar
import org.akrck02.skyleriearts.ui.component.gallery.GalleryImage
import org.akrck02.skyleriearts.ui.component.input.IconButtonBasicData
import org.akrck02.skyleriearts.ui.component.input.MaterialTextField
import org.akrck02.skyleriearts.ui.component.modal.MaterialAlertInputDialog
import org.akrck02.skyleriearts.ui.component.tag.TagContainer
import org.akrck02.skyleriearts.ui.model.GalleryImage
import org.akrck02.skyleriearts.ui.model.filter.TagType
import org.akrck02.skyleriearts.viewmodel.AppViewModel
import org.akrck02.skyleriearts.viewmodel.ImageDetailViewModel
import org.jetbrains.compose.resources.stringResource
import skylerieartsuploader.composeapp.generated.resources.Res
import skylerieartsuploader.composeapp.generated.resources.addNew
import skylerieartsuploader.composeapp.generated.resources.addTo
import skylerieartsuploader.composeapp.generated.resources.categories
import skylerieartsuploader.composeapp.generated.resources.description
import skylerieartsuploader.composeapp.generated.resources.name
import skylerieartsuploader.composeapp.generated.resources.noThingsHereAddOne
import skylerieartsuploader.composeapp.generated.resources.notNow
import skylerieartsuploader.composeapp.generated.resources.projects
import skylerieartsuploader.composeapp.generated.resources.willOrderYourImagesWebsite
import java.util.Locale

/**
 * ImageDetailView
 */
@Composable
fun ImageDetailView(appViewModel: AppViewModel, viewModel: ImageDetailViewModel) {

    println("Details for image ${viewModel.imageData}")

    Column(modifier = Modifier.fillMaxSize()) {
        ControlsBar(getButtonControls(appViewModel, viewModel.imageData))
        ImageDetailComponent(
            image = viewModel.imageData,
            onProjectAdd = viewModel::addProject,
            onProjectRemove = viewModel::removeProject,
            onCategoryAdd = viewModel::addCategory,
            onCategoryRemoved = viewModel::removeCategory,
            onNameValueChange = viewModel::setName,
            onDescriptionValueChange = viewModel::setDescription,
            onImageClick = {
                ImageFullScreenRoute.image = it
                appViewModel.navigate(ImageFullScreenRoute)
            }
        )
    }
}

/**
 * Get button controls
 */
private fun getButtonControls(
    appViewModel: AppViewModel,
    imageData: GalleryImage
) = listOf(
    IconButtonBasicData(
        icon = Icons.Rounded.DeleteOutline,
        description = "Remove",

        onClick = {
//            ImageProcessor.deleteFromGallery(imageData, gallery)

            // navigate to gallery
            appViewModel.navigate(ProjectsRoute)
        },
    ),
    IconButtonBasicData(
        icon = Icons.Rounded.Close,
        description = "Close",
        onClick = { appViewModel.navigate(ProjectsRoute) }
    )
)


/**
 * Image detail component
 */
@Composable
private fun ImageDetailComponent(
    image: GalleryImage,
    onProjectAdd: (String) -> Unit,
    onProjectRemove: (String) -> Unit,
    onCategoryAdd: (String) -> Unit,
    onCategoryRemoved: (String) -> Unit,
    onNameValueChange: (String) -> Unit,
    onDescriptionValueChange: (String) -> Unit,
    onImageClick: (GalleryImage) -> Unit
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
    imageData: GalleryImage,
    onNameValueChange: (String) -> Unit,
    onDescriptionValueChange: (String) -> Unit,
    onImageClick: (GalleryImage) -> Unit
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