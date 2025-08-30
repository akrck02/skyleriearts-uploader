package org.akrck02.skyleriearts.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.akrck02.skyleriearts.data.PortfolioDataAccess

class ProjectAddViewModel(val portfolioDataAccess: PortfolioDataAccess) : ViewModel() {

    // region state
    var name by mutableStateOf("")
    var projectCategories = mutableStateListOf<String>()
    var globalCategories = mutableStateListOf<String>()
    // endregion state

    init {
        loadCategories()
    }

    fun loadCategories() {
        globalCategories.addAll(portfolioDataAccess.getCategories().sorted())
    }

    fun saveCurrentProject() {
        projectCategories.forEach { portfolioDataAccess.insertProject(name, it) }
        portfolioDataAccess.savePortfolio()
        portfolioDataAccess.print()
        clear()
        loadCategories()
    }

    fun clear() {
        name = ""
        projectCategories.clear()
    }
}