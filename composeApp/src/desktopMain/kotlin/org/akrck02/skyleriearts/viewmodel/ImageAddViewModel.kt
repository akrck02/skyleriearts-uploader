package org.akrck02.skyleriearts.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.akrck02.skyleriearts.data.PortfolioDataAccess

class ImageAddViewModel(val portfolioDataAccess: PortfolioDataAccess) : ViewModel() {

    // region variables
    private var _projects = mutableListOf<String>()
    // endregion variables

    // region state
    var projects by mutableStateOf(_projects)
    var selectedProject by mutableStateOf("")
    // endregion state

    init {
        loadProjects()
    }

    fun loadProjects() {
        projects = portfolioDataAccess.getProjects().sorted().toMutableList()
        selectedProject = projects.firstOrNull() ?: ""
    }

}