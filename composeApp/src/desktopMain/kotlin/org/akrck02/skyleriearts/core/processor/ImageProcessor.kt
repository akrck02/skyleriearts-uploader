package org.akrck02.skyleriearts.core.processor

import androidx.compose.runtime.snapshots.SnapshotStateMap
import org.akrck02.skyleriearts.data.constant.Paths
import org.akrck02.skyleriearts.data.model.Image
import org.akrck02.skyleriearts.service.FileService
import java.io.File

object ImageProcessor {


    /**
     * Add an image file to gallery
     * @param gallery The gallery of photos
     * @return The method to handle the image addition
     */
    fun addImageFileToGallery(
        file: File,
        gallery: SnapshotStateMap<String, Image>
    ) {

        // Get the data.
        val data = gallery[file.name] ?: Image(
            name = file.name,
            path = Paths.getUploadsPath(file.name),
            minPath = Paths.getThumbnailsPath(file.name)
        )

        //data.new = true

        // If data does not exist in database, add it
        gallery[data.name] = data
    }

    fun deleteFromGallery(
        imageData: Image,
        gallery: SnapshotStateMap<String, Image>
    ) {
        // remove the resources
        FileService.remove(imageData.path)
        FileService.remove(imageData.minPath)

        // remove the data
        gallery.remove(imageData.name)
        FileService.saveGallery(gallery)
    }

}
