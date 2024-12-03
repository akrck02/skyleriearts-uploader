package org.akrck02.skyleriearts.core

import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.akrck02.skyleriearts.model.ImageData
import org.jetbrains.skia.Image
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

/**
 * Load an image from path
 * @param path The file path
 * @return The loaded image
 */
fun loadImageFrom(path: String): ImageBitmap {
    val bytes = Files.readAllBytes(Path.of(path)) // path relative to project root
    return Image.makeFromEncoded(bytes).toComposeImageBitmap()
}

/**
 * Add an image file to gallery
 * @param gallery The gallery of photos
 * @return The method to handle the image addition
 */
fun addImageFileToGallery(
    gallery: SnapshotStateMap<String, ImageData>
) = { file: File ->

    // Get the data.
    val data = gallery[file.name] ?: ImageData(
        name = file.name,
        path = getResourcesPath(file.name),
        minPath = getThumbnailPath(file.name)
    )

    data.new = true

    // If data does not exist in database, add it
    gallery[data.name] = data
}

fun deleteFromGallery(
    imageData: ImageData,
    gallery: SnapshotStateMap<String, ImageData>
) {
    // remove the resources
    removeFile(imageData.path)
    removeFile(imageData.minPath)

    // remove the data
    gallery.remove(imageData.name)
    saveGalleryToFile(gallery)
}