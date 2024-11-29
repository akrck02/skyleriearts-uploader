package org.akrck02.skyleriearts.core

import androidx.compose.runtime.snapshots.SnapshotStateMap
import com.eygraber.uri.UriCodec
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.akrck02.skyleriearts.model.ImageData
import java.io.File

/**
 * Add the file to the queue if needed
 * if the file already exists, returns
 * the current file.
 */
fun addFileToResources(filepath: String): File? {

    // If it is directory return
    val file = File(filepath)
    if (file.isDirectory)
        return null

    // If extension is invalid return
    if (validExtensions.contains(file.extension).not())
        return null

    // if the file exists return the file
    var newFile = File(getResourcesPath(file.name))
    if (newFile.exists())
        newFile.delete()

    // Copy the file and return
    newFile = file.copyTo(newFile)

    // compress the image
    compress(newFile.path)
    return newFile
}


/**
 * Remove a file
 */
fun removeFile(filepath: String) {

    // If it is directory return
    val file = File(filepath)
    if (file.isDirectory)
        throw RuntimeException("Cannot delete: File $filepath is a directory.")

    // If file cannot be deleted
    if (file.exists().not())
        throw RuntimeException("Cannot delete: File $filepath doesn't exist.")

    // If file cannot be deleted
    if(file.delete().not())
        throw RuntimeException("Cannot delete: File $filepath.")

}

/**
 * Remove a file from resources
 * @param filepath The file path
 */
fun removeResourceFile(filepath : String) {
    removeFile(getResourcesPath(filepath))
}

/**
 * Remove thumbnail file
 * @param filepath The file path
 */
fun removeThumbnailFile(filepath: String) {
    removeFile(getThumbnailPath(filepath))
}


/**
 * Get current gallery from file
 */
fun getCurrentGalleryFromFile(): SnapshotStateMap<String, ImageData> {

    val currentFile = File("images.json")
    if (currentFile.exists().not()) {
        currentFile.writeText(
            Json.encodeToString<Map<String, ImageData>>(mapOf()),
            Charsets.UTF_8
        )
    }

    val jsonData = currentFile.readText(Charsets.UTF_8)
    val map = Json.decodeFromString<Map<String, ImageData>>(UriCodec.decode(jsonData))
    return buildMutableStateMap {
        map.entries.forEach { (k, v) ->
            put(k, v)
        }
    }
}


/**
 * Get current gallery from file
 */
fun saveGalleryToFile(gallery: Map<String, ImageData>) {

    val currentFile = File("images.json")
    currentFile.writeText(
        Json.encodeToString<Map<String, ImageData>>(gallery),
        Charsets.UTF_8
    )

}