package org.akrck02.skyleriearts.core

import androidx.compose.runtime.snapshots.SnapshotStateMap
import com.eygraber.uri.UriCodec
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.akrck02.skyleriearts.exception.ErrorCode
import org.akrck02.skyleriearts.exception.SoftwareException
import org.akrck02.skyleriearts.model.ImageData
import java.io.File

/**
 * Add the file to the queue if needed
 * if the file already exists, returns
 * the current file.
 * @param path The file path
 * @return The new file
 */
fun addFileToResources(path: String): File {

    // If it is directory return
    val file = File(path)
    if (file.isDirectory)
        throw SoftwareException(
            code = ErrorCode.FilePathIsDirectory,
            message = "Cannot delete: File $path is a directory."
        )
    // If extension is invalid return
    if (validExtensions.contains(file.extension).not())
        throw SoftwareException(
            code = ErrorCode.InvalidFileExtension,
            message = "Cannot delete: File $path has an invalid extension (${file.extension})."
        )

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
 * @param path The file path
 * @throws SoftwareException if an error occurs
 */
fun removeFile(path: String) {

    // If it is directory return
    val file = File(path)
    if (file.isDirectory)
        throw SoftwareException(
            code = ErrorCode.FilePathIsDirectory,
            message = "Cannot delete: File $path is a directory."
        )

    // If file cannot be deleted
    if (file.exists().not())
        throw SoftwareException(
            code = ErrorCode.FileDoesNotExist,
            message = "Cannot delete: File $path doesn't exist."
        )

    // If file cannot be deleted
    if (file.delete().not())
        throw SoftwareException(
            code = ErrorCode.CannotDeleteFile,
            message = "Cannot delete: File $path."
        )

}


private const val GALLERY_FILE_PATH = "images.json"

/**
 * Get current gallery from file,
 * create the file if it does not exist
 * @return The map of names and image data
 */
fun getCurrentGalleryFromFile(): SnapshotStateMap<String, ImageData> {

    val currentFile = File(GALLERY_FILE_PATH)
    if (currentFile.exists().not()) {
        currentFile.writeText(
            Json.encodeToString<Map<String, ImageData>>(mapOf()),
            Charsets.UTF_8
        )
    }

    val jsonData = currentFile.readText(Charsets.UTF_8)
    val map = Json.decodeFromString<Map<String, ImageData>>(UriCodec.decode(jsonData))
    return buildMutableStateMap {
        map.entries.forEach { (k, v) -> put(k, v) }
    }
}


/**
 * Save the current galley data to a file
 * @param gallery The gallery data (image name -> image data)
 */
fun saveGalleryToFile(gallery: Map<String, ImageData>) {

    gallery.entries.forEach { (k, v) -> gallery[k]?.new = false }

    val currentFile = File(GALLERY_FILE_PATH)
    currentFile.writeText(
        Json.encodeToString<Map<String, ImageData>>(gallery),
        Charsets.UTF_8
    )

}