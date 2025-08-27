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
    var selectedCategory by mutableStateOf("")
    // endregion state

    init {
        loadCategories()
    }

    fun loadCategories() {
        globalCategories.addAll(portfolioDataAccess.getCategories().sorted())
        selectedCategory = globalCategories.firstOrNull() ?: ""
    }

    fun suputamadre(silksong: String) {
        projectCategories.add(silksong)
    }

}