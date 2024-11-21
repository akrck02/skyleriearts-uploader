package org.akrck02.skyleriearts.model

import kotlinx.serialization.Serializable

/**
 * Image data for register and parse
 */
@Serializable
class ImageData(
    var name: String,
    val path: String,
) {
    var description: String = ""
    var tags: MutableSet<String> = mutableSetOf()
    var projects: MutableSet<String> = mutableSetOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ImageData

        if (name != other.name) return false
        if (path != other.path) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + path.hashCode()
        return result
    }
}