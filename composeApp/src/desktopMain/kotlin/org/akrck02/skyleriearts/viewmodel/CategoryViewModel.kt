package org.akrck02.skyleriearts.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.akrck02.skyleriearts.constant.ProjectsRoute
import org.akrck02.skyleriearts.data.GalleryDataAccess
import org.akrck02.skyleriearts.ui.model.filter.ProjectListFilter

class CategoryViewModel(val galleryDataAccess: GalleryDataAccess) : ViewModel() {

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
            ProjectListFilter.Name -> galleryDataAccess.searchCategoriesByName("")
            ProjectListFilter.Category -> galleryDataAccess.getCategories()
            else -> galleryDataAccess.getCategories()
        }.toMutableList()
    }

    fun getImageNumberOfCategory(name: String): Int {
        return galleryDataAccess.getImagesByCategory(name).size
    }

    fun getProjectNumberOfCategory(name: String): Int {
        return galleryDataAccess.getProjectsByCategory(name).size
    }
}