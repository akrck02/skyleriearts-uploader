package org.akrck02.skyleriearts.ui.model

import androidx.compose.runtime.mutableStateListOf
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.akrck02.skyleriearts.data.model.Image

/**
 * This class represents an image on the UI
 */
@Serializable
data class GalleryImage(
    var name: String,
    var path: String,
    var minPath: String,
) {
    var description: String = ""
    var categories: MutableList<String> = mutableStateListOf()
    var projects: MutableList<String> = mutableStateListOf()

    @Transient
    var new: Boolean = true

    @Transient
    var selected: Boolean = false
}

/**
 * Convert a gallery image to an image model
 */
fun GalleryImage.toImage(): Image = Image(
    this.name,
    this.path,
    this.minPath
).apply {
    description = this.description
    categories = this.categories
    projects = this.categories
}

/**
 * Convert an image to a gallery image
 */
fun Image.toGalleryImage(): GalleryImage = GalleryImage(
    this.name,
    this.path,
    this.minPath
).apply {
    description = this.description
    categories = this.categories
    projects = this.categories
}


