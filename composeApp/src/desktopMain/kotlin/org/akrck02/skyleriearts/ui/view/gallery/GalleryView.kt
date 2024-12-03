package org.akrck02.skyleriearts.ui.view.gallery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesomeMosaic
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.SelectAll
import androidx.compose.material.icons.outlined.TaskAlt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.gallery
import kotlinproject.composeapp.generated.resources.numberOfImages
import org.akrck02.skyleriearts.core.deleteFromGallery
import org.akrck02.skyleriearts.core.toggle
import org.akrck02.skyleriearts.model.ImageData
import org.akrck02.skyleriearts.navigation.ImageDetailRoute
import org.akrck02.skyleriearts.navigation.NavigationType
import org.akrck02.skyleriearts.navigation.navigateSecurely
import org.akrck02.skyleriearts.ui.gallery.GalleryImage
import org.akrck02.skyleriearts.ui.input.IconButton
import org.akrck02.skyleriearts.ui.input.IconButtonBasicData
import org.akrck02.skyleriearts.ui.theme.TOTAL_ROUNDED_SHAPE
import org.jetbrains.compose.resources.stringResource

/**
 * The image gallery view
 *
 * @param navController The navigation controller
 * @param gallery The gallery to show
 */
@Composable
fun GalleryView(navController: NavHostController, gallery: SnapshotStateMap<String, ImageData>) {

    var selectionMode by remember { mutableStateOf(false) }
    var selectAll by remember { mutableStateOf(false) }
    val selectedImages = remember { mutableMapOf<String, Int>() }

    Column(modifier = Modifier.fillMaxSize()) {
        GalleryViewHeader(
            gallery = gallery,
            selectedImages = selectedImages,
            onSelectAllModeToggled = {
                selectionMode = true
                selectAll = !selectAll
            },
            onSelectionModeToggled = {
                selectionMode = !selectionMode
                selectAll = false
            }
        )

        val minSize = 150.dp
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize),
            modifier = GalleryViewDefault.lazyGridModifier,
            verticalArrangement = Arrangement.Top,
        ) {

            val keys: MutableList<String> = mutableListOf()
            keys.addAll(gallery.keys)

            items(keys, key = { it }) {
                val image = gallery[it]!!
                var selected by remember { mutableStateOf(false) }

                if (selectionMode && selectAll) {
                    selected = true
                }

                GalleryImage(
                    data = image,
                    modifier = GalleryViewDefault.imageModifier(minSize),
                    selected = selected,
                    onClick = {

                        if (selectionMode) {
                            selectedImages.toggle(
                                key = it,
                                onAdd = { selected = true },
                                onRemove = { selected = false }
                            )

                            selectAll = false
                        } else {
                            navController.navigateSecurely(
                                route = ImageDetailRoute(item = NavigationType(imageData = image))
                            )
                        }
                    }
                )

            }
        }
    }
}

/**
 * The header for the gallery view
 *
 * @param gallery The gallery to control
 * @param onSelectionModeToggled Callback for selection mode toggle
 * @param onSelectAllModeToggled Callback on select all mode toggle
 */
@Composable
private fun GalleryViewHeader(
    gallery: SnapshotStateMap<String, ImageData>,
    selectedImages: MutableMap<String, Int>,
    onSelectionModeToggled: () -> Unit,
    onSelectAllModeToggled: () -> Unit
) {
    Surface(
        shape = TOTAL_ROUNDED_SHAPE,
        modifier = Modifier.fillMaxWidth().height(100.dp)
            .padding(20.dp),
        color = Color(0xFFE7E5E1),
        contentColor = MaterialTheme.colors.primary,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            GalleryViewHeaderTitle(gallery)
            GalleryViewHeaderControls(
                gallery = gallery,
                selectedImages = selectedImages,
                onSelectionModeToggled = onSelectionModeToggled,
                onSelectAllModeToggled = onSelectAllModeToggled
            )
        }
    }
}

/**
 * The header title
 *
 * @param gallery The current gallery
 */
@Composable
private fun GalleryViewHeaderTitle(gallery: SnapshotStateMap<String, ImageData>) {
    Row {
        Icon(
            imageVector = Icons.Outlined.AutoAwesomeMosaic,
            contentDescription = stringResource(Res.string.gallery),
            modifier = Modifier.padding(15.dp).size(35.dp)
        )

        Text(
            text = stringResource(Res.string.numberOfImages, gallery.size),
            modifier = Modifier.padding(top = 12.dp),
            fontSize = 1.5.em
        )
    }
}

/**
 * The controls of the gallery view
 *
 * @param onSelectionModeToggled Callback for selection mode toggle
 * @param onSelectAllModeToggled Callback on select all mode toggle
 */
@Composable
private fun GalleryViewHeaderControls(
    gallery: SnapshotStateMap<String, ImageData>,
    selectedImages: MutableMap<String, Int>,
    onSelectionModeToggled: () -> Unit,
    onSelectAllModeToggled: () -> Unit
) {

    var selected by remember { mutableStateOf(false) }
    Row {

        if (selected) {
            IconButton(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = MaterialTheme.colors.primary
                ),
                data = IconButtonBasicData(
                    icon = Icons.Outlined.DeleteOutline,
                    description = "Delete",
                    onClick = {
                        selectedImages.forEach { (key, _) ->
                            val imageData: ImageData? = gallery[key]

                            imageData?.let {
                                deleteFromGallery(imageData, gallery)
                            }
                        }
                    }
                ),
                modifier = Modifier.height(70.dp),
                iconModifier = Modifier.size(30.dp)
            )
        }


        IconButton(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = MaterialTheme.colors.primary
            ),
            data = IconButtonBasicData(
                icon = if (selected) Icons.Outlined.Circle else Icons.Outlined.TaskAlt,
                description = "Select",
                onClick = {
                    selected = !selected
                    onSelectionModeToggled()
                }
            ),
            modifier = Modifier.height(70.dp),
            iconModifier = Modifier.size(30.dp)
        )

        IconButton(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = MaterialTheme.colors.primary
            ),
            data = IconButtonBasicData(
                icon = Icons.Outlined.SelectAll,
                description = "Select all",
                onClick = {
                    selected = true
                    onSelectAllModeToggled()
                }
            ),
            modifier = Modifier.height(70.dp),
            iconModifier = Modifier.size(30.dp)
        )
    }
}