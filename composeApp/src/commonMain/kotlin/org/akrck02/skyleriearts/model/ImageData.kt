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
class ImageData(
    var name: String,
    val path: String,
    val minPath: String,
) {
    var description: String = ""
    var categories: MutableList<String> = mutableStateListOf()
    var projects: MutableList<String> = mutableStateListOf()

    @Transient
    var new: Boolean = true

    @Transient
    var selected: Boolean = false

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ImageData

        return name == other.name
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + path.hashCode()
        return result
    }
}