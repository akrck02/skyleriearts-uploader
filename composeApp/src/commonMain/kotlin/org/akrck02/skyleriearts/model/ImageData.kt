package org.akrck02.skyleriearts.model

import androidx.compose.runtime.mutableStateListOf
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * This class represents an image uploaded to the portfolio
 * images may belong to projects and have tags, as well as
 * a compressed version.
 */
@Serializable
data class ImageData(
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