package org.akrck02.skyleriearts.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.akrck02.skyleriearts.constant.ProjectsRoute
import org.akrck02.skyleriearts.data.GalleryDataAccess
import org.akrck02.skyleriearts.ui.model.filter.ProjectListFilter

class CategoryViewModel(galleryDataAccess: GalleryDataAccess) : ViewModel() {

    // region variables
    private var _categories = mutableListOf<String>()
    // endregion variables

    // region state
    var categories by mutableStateOf(_categories)
    // endregion state

    init {
        loadCategories(galleryDataAccess)
    }

    fun loadCategories(galleryDataAccess: GalleryDataAccess) {
        categories = when (ProjectsRoute.filter) {
            ProjectListFilter.Name -> galleryDataAccess.searchCategoriesByName("")
            ProjectListFilter.Category -> galleryDataAccess.getCategories()
            else -> galleryDataAccess.getCategories()
        }.toMutableList()
    }
}