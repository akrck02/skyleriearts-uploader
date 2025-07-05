package org.akrck02.skyleriearts.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.akrck02.skyleriearts.constant.ProjectsRoute
import org.akrck02.skyleriearts.data.PortfolioDataAccess
import org.akrck02.skyleriearts.ui.model.filter.ProjectListFilter

class CategoryViewModel(val portfolioDataAccess: PortfolioDataAccess) : ViewModel() {

    // region variables
    private var _categories = mutableListOf<String>()
    // endregion variables

    // region state
    var categories by mutableStateOf(_categories)
    // endregion state

    init {
        loadCategories()
    }

    fun loadCategories() {
        categories = when (ProjectsRoute.filter) {
            ProjectListFilter.Name -> portfolioDataAccess.searchCategoriesByName("")
            ProjectListFilter.Category -> portfolioDataAccess.getCategories()
            else -> portfolioDataAccess.getCategories()
        }.toMutableList()
    }

    fun getImageNumberOfCategory(name: String): Int {
        return portfolioDataAccess.getImagesByCategory(name).size
    }

    fun getProjectNumberOfCategory(name: String): Int {
        return portfolioDataAccess.getProjectsByCategory(name).size
    }
}