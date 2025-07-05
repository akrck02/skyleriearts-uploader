package org.akrck02.skyleriearts.data.model

import androidx.compose.runtime.mutableStateListOf
import kotlinx.serialization.Serializable

/**
 * This class represents an image uploaded to the portfolio
 * images may belong to projects and have tags, as well as
 * a compressed version.
 */
@Serializable
data class Image(
    var name: String,
    var path: String,
    var minPath: String,
) {

    var description: String = ""
    var categories: MutableList<String> = mutableStateListOf()
    var projects: MutableList<String> = mutableStateListOf()
}