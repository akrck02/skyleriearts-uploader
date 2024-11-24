package org.akrck02.skyleriearts.core

import androidx.compose.runtime.snapshots.SnapshotStateList
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
 * @param imagesToUpload The images to upload
 * @return The method to handle the image addition
 */
fun addImageFileToGallery(
    gallery: SnapshotStateMap<String, ImageData>,
    imagesToUpload: SnapshotStateList<ImageData>
) = { file: File ->

    // Get the data.
    var data = gallery[file.name]

    // If data does not exist in database, add it
    if (data == null) {
        data = ImageData(
            name = file.name,
            path = getResourcesPath(file.name),
            minPath = getThumbnailPath(file.name)
        )

        gallery[data.name] = data
    }

    // if it is a new image to upload, add it
    if (imagesToUpload.contains(data).not()) {
        imagesToUpload.add(data)
    }

    saveGalleryToFile(gallery)

}