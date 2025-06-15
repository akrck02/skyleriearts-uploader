package org.akrck02.skyleriearts.ui.view.image

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import org.akrck02.skyleriearts.core.Paths
import org.akrck02.skyleriearts.model.ImageData
import org.akrck02.skyleriearts.ui.component.gallery.GalleryImage
import org.akrck02.skyleriearts.ui.component.header.ListHeader
import org.akrck02.skyleriearts.ui.view.projects.ProjectViewDefault
import org.akrck02.skyleriearts.ui.view.projects.SelectionMode
import org.akrck02.skyleriearts.viewmodel.AppViewModel

@Composable
fun ImagesView(appViewModel: AppViewModel) {

    val gallery = appViewModel.gallery

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ListHeader("You have ${gallery.size} drawings.")
        LazyGallery(
            gallery = gallery,
            selectionMode = SelectionMode.None,
            onImageClick = {},
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
    gallery: SnapshotStateMap<String, ImageData>,
    selectionMode: SelectionMode,
    onImageClick: (ImageData) -> Unit,
    onSelectedImageToggle: (ImageData) -> Unit
) {
    val minSize = 150.dp
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize),
        modifier = ProjectViewDefault.lazyGridModifier,
        verticalArrangement = Arrangement.Top,
    ) {

        val keys: MutableList<String> = mutableListOf()
        keys.addAll(gallery.keys)

        items(keys, key = { it }) {

            val image by remember {
                mutableStateOf(gallery[it]!!.let {
                    it.copy(path = "${Paths.basePath}/${it.path}", minPath = "${Paths.basePath}/${it.minPath}")
                })
            }

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
