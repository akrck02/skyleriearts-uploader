package org.akrck02.skyleriearts.ui.view.image

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import org.akrck02.skyleriearts.constant.ImageDetailRoute
import org.akrck02.skyleriearts.constant.ImagesRoute
import org.akrck02.skyleriearts.ui.component.gallery.GalleryImage
import org.akrck02.skyleriearts.ui.component.header.FilterHeader
import org.akrck02.skyleriearts.ui.component.header.ListHeader
import org.akrck02.skyleriearts.ui.model.GalleryImage
import org.akrck02.skyleriearts.ui.model.filter.GalleryFilter
import org.akrck02.skyleriearts.ui.view.projects.ProjectViewDefault
import org.akrck02.skyleriearts.ui.view.projects.SelectionMode
import org.akrck02.skyleriearts.viewmodel.AppViewModel
import org.akrck02.skyleriearts.viewmodel.GalleryViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ImagesView(appViewModel: AppViewModel, galleryViewModel: GalleryViewModel = koinViewModel()) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ListHeader("You have ${galleryViewModel.images.size} drawings.")
        ImagesRoute.filter.takeIf { GalleryFilter.None != it }?.also {
            ImagesRoute.filterObjectId?.also { filter ->
                FilterHeader(listOf(filter to Icons.Rounded.Palette)) {
                    ImagesRoute.filter = GalleryFilter.None
                    galleryViewModel.reload()
                }
            }
        }

        LazyGallery(
            gallery = galleryViewModel.images,
            selectionMode = SelectionMode.None,
            onImageClick = {
                ImageDetailRoute.image = it
                appViewModel.navigate(ImageDetailRoute)
            },
            onSelectedImageToggle = {}
        )
    }
}


/**
 * Lazy image gallery
 *
 * @param gallery The gallery
 * @param selectionMode The selection mode
 * @param onImageClick Callback for regular mode image click
 * @param onSelectedImageToggle Callback for image selection
 */
@Composable
private fun LazyGallery(
    gallery: MutableList<GalleryImage>,
    selectionMode: SelectionMode,
    onImageClick: (GalleryImage) -> Unit,
    onSelectedImageToggle: (GalleryImage) -> Unit
) {
    val minSize = 150.dp
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize),
        modifier = ProjectViewDefault.lazyGridModifier,
        verticalArrangement = Arrangement.Top,
    ) {

        items(gallery, key = { it }) { image ->

            var selected by remember { mutableStateOf(image.selected) }

            GalleryImage(
                data = image,
                modifier = ProjectViewDefault.imageModifier(minSize),
                selected = (selectionMode == SelectionMode.SelectAll || selectionMode == SelectionMode.Select) && selected,
                grayscale = (selectionMode == SelectionMode.SelectAll || selectionMode == SelectionMode.Select) && selected.not(),
                onClick = {
                    when (selectionMode) {
                        SelectionMode.SelectAll,
                        SelectionMode.Select -> {
                            onSelectedImageToggle(image)
                            selected = image.selected
                        }

                        SelectionMode.None -> {
                            onImageClick(image)
                        }
                    }
                }
            )

        }
    }
}
