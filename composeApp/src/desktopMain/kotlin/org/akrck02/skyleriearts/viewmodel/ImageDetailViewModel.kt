package org.akrck02.skyleriearts.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.akrck02.skyleriearts.data.PortfolioDataAccess
import org.akrck02.skyleriearts.extension.addIfNotPresent
import org.akrck02.skyleriearts.extension.removeIfPresent
import org.akrck02.skyleriearts.ui.model.GalleryImage
import java.util.Locale

class ImageDetailViewModel(val dataAccess: PortfolioDataAccess) : ViewModel() {


    var imageData by mutableStateOf(GalleryImage("", "", ""))

    fun addProject(project: String) {
        imageData.projects.addIfNotPresent(project.lowercase(Locale.getDefault()))
    }

    fun removeProject(project: String) {
        imageData.projects.removeIfPresent(project.lowercase(Locale.getDefault()))
    }

    fun addCategory(category: String) {
        imageData.categories.addIfNotPresent(category.lowercase(Locale.getDefault()))
    }

    fun removeCategory(category: String) {
        imageData.categories.removeIfPresent(category.lowercase(Locale.getDefault()))
    }

    fun getCategories(): Set<String> {
        return dataAccess.getCategoriesOfImage(imageData.name)
    }

    fun setName(name: String) {
        imageData.name = name
    }

    fun setDescription(description: String) {
        imageData.description = description
    }


}