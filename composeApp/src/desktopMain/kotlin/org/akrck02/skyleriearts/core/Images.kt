package org.akrck02.skyleriearts.core

import androidx.compose.runtime.snapshots.SnapshotStateMap
import org.akrck02.skyleriearts.model.ImageData
import java.io.File


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