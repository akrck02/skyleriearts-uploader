package org.akrck02.skyleriearts.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.akrck02.skyleriearts.data.PortfolioDataAccess

class ProjectAddViewModel(val portfolioDataAccess: PortfolioDataAccess) : ViewModel() {

    // region variables
    private var _name = ""
    private var _globalCategories = mutableListOf<String>()
    private var _projectCategories = mutableListOf<String>()
    // endregion variables

    // region state
    var name by mutableStateOf(_name)
    var projectCategories by mutableStateOf(_projectCategories)
    var globalCategories by mutableStateOf(_globalCategories)
    var selectedCategory by mutableStateOf("")
    // endregion state

    init {
        loadCategories()
    }

    fun loadCategories() {
        globalCategories = portfolioDataAccess.getCategories().sorted().toMutableList()
        selectedCategory = globalCategories.firstOrNull() ?: ""
    }

}