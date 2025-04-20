package org.akrck02.skyleriearts.ui.view.image.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.akrck02.skyleriearts.core.addIfNotPresent
import org.akrck02.skyleriearts.core.removeIfPresent
import org.akrck02.skyleriearts.model.ImageData
import java.util.Locale

class ImageDetailViewModel : ViewModel() {


    var imageData by mutableStateOf(ImageData("", "", ""))

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

    fun setName(name: String) {
        imageData.name = name
    }

    fun setDescription(description: String) {
        imageData.description = description
    }


}