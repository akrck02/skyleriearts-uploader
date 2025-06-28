package org.akrck02.skyleriearts.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.akrck02.skyleriearts.constant.ImagesRoute
import org.akrck02.skyleriearts.data.GalleryDataAccess
import org.akrck02.skyleriearts.data.constant.Paths
import org.akrck02.skyleriearts.ui.model.GalleryImage
import org.akrck02.skyleriearts.ui.model.filter.GalleryFilter
import org.akrck02.skyleriearts.ui.model.toGalleryImage

class GalleryViewModel(val galleryDataAccess: GalleryDataAccess) : ViewModel() {

    // region variables
    private var _images = mutableListOf<GalleryImage>()
    // endregion variables

    // region state
    var images by mutableStateOf(_images)
    // endregion state

    init {
        loadGallery()
    }

    fun loadGallery() {
        images = when (ImagesRoute.filter) {
            GalleryFilter.Category -> galleryDataAccess.getImagesByCategory(ImagesRoute.filterObjectId ?: "")
            GalleryFilter.Project -> galleryDataAccess.getImagesByProject(ImagesRoute.filterObjectId ?: "")
            else -> galleryDataAccess.getImages()
        }.map {
            it.toGalleryImage().apply {
                path = "${Paths.basePath}/$path"
                minPath = "${Paths.basePath}/$minPath"
            }
        }.toMutableList()
    }


    fun reload() {
        loadGallery()
    }
}