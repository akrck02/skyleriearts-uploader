package org.akrck02.skyleriearts.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.akrck02.skyleriearts.model.ProjectListFilter
import org.akrck02.skyleriearts.navigation.ProjectsRoute

class ProjectsViewModel(appViewModel: AppViewModel) : ViewModel() {

    // region variables
    private var _projects = mutableListOf<String>()
    // endregion variables

    // region state
    var projects by mutableStateOf(_projects)
    // endregion state

    init {
        loadProjects(appViewModel)
    }

    fun loadProjects(appViewModel: AppViewModel) {
        projects = when (ProjectsRoute.filter) {
            ProjectListFilter.Name -> mutableListOf()
            ProjectListFilter.Category -> appViewModel.categoryMap[ProjectsRoute.filterObjectId]?.toMutableList() ?: mutableListOf()
            else -> appViewModel.projectMap.keys.toMutableList()
        }.sorted() as MutableList<String>
    }

}