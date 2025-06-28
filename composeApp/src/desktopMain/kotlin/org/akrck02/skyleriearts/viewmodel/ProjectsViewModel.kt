package org.akrck02.skyleriearts.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.akrck02.skyleriearts.constant.ProjectsRoute
import org.akrck02.skyleriearts.data.GalleryDataAccess
import org.akrck02.skyleriearts.ui.model.filter.ProjectListFilter

class ProjectsViewModel(val galleryDataAccess: GalleryDataAccess) : ViewModel() {

    // region variables
    private var _projects = mutableListOf<String>()
    // endregion variables

    // region state
    var projects by mutableStateOf(_projects)
    // endregion state

    init {
        reload()
    }

    fun loadProjects() {
        projects = when (ProjectsRoute.filter) {
            ProjectListFilter.Name -> galleryDataAccess.searchProjectsByName(ProjectsRoute.filterObjectId ?: "")
            ProjectListFilter.Category -> galleryDataAccess.getProjectsByCategory(ProjectsRoute.filterObjectId ?: "")
            else -> galleryDataAccess.getProjects()
        }.toMutableList()
    }

    fun reload() {
        loadProjects()
    }

}