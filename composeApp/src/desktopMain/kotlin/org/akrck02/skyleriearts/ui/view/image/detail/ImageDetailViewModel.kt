package org.akrck02.skyleriearts.ui.view.image.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.akrck02.skyleriearts.core.addIfNotPresent
import org.akrck02.skyleriearts.core.removeIfPresent
import org.akrck02.skyleriearts.model.ImageData
import java.util.Locale

class ImageDetailViewModel(
    data: ImageData
) : ViewModel() {

    private val _uiState = MutableStateFlow(data)
    val uiState: StateFlow<ImageData> = _uiState.asStateFlow()

    fun addProject(project: String) {
        _uiState.update {
            it.also { it.projects.addIfNotPresent(project.lowercase(Locale.getDefault())) }
        }
    }

    fun removeProject(project: String) {
        _uiState.update {
            it.also { it.projects.removeIfPresent(project.lowercase(Locale.getDefault())) }
        }
    }

    fun addCategory(category: String) {
        _uiState.update {
            it.also { it.categories.addIfNotPresent(category.lowercase(Locale.getDefault())) }
        }
    }

    fun removeCategory(category: String) {
        _uiState.update {
            it.also { it.categories.removeIfPresent(category.lowercase(Locale.getDefault())) }
        }
    }

    fun setName(name: String) {
        _uiState.update {
            it.also { it.name = name }
        }
    }

    fun setDescription(description: String) {
        _uiState.update {
            it.also { it.description = description }
        }
    }


}