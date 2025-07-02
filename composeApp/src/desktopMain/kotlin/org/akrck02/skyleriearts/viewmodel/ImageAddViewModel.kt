package org.akrck02.skyleriearts.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.akrck02.skyleriearts.data.GalleryDataAccess

class ImageAddViewModel(val galleryDataAccess: GalleryDataAccess) : ViewModel() {

    // region variables
    private var _projects = mutableListOf<String>()
    // endregion variables

    // region state
    var projects by mutableStateOf(_projects)
    // endregion state

    init {
        loadProjects()
    }

    fun loadProjects() {
        projects = galleryDataAccess.getProjects().sorted().toMutableList()
    }

}