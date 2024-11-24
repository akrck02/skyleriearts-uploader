package org.akrck02.skyleriearts.model

import kotlinx.serialization.Serializable

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
    var tags: MutableSet<String> = mutableSetOf()
    var projects: MutableSet<String> = mutableSetOf()

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