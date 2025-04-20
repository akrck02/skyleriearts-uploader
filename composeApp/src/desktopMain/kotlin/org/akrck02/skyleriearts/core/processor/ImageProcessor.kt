package org.akrck02.skyleriearts.core.processor

import androidx.compose.runtime.snapshots.SnapshotStateMap
import org.akrck02.skyleriearts.core.Paths
import org.akrck02.skyleriearts.core.command.execute
import org.akrck02.skyleriearts.model.ImageData
import java.io.File

object ImageProcessor {


    const val COMPRESSOR_BINARY_PATH: String = "./bin/image-compressor"

    /**
     * Add an image file to gallery
     * @param gallery The gallery of photos
     * @return The method to handle the image addition
     */
    fun addImageFileToGallery(
        file: File,
        gallery: SnapshotStateMap<String, ImageData>
    ) {

        // Get the data.
        val data = gallery[file.name] ?: ImageData(
            name = file.name,
            path = Paths.getUploadsPath(file.name),
            minPath = Paths.getThumbnailsPath(file.name)
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
        FileProcessor.removeFile(imageData.path)
        FileProcessor.removeFile(imageData.minPath)

        // remove the data
        gallery.remove(imageData.name)
        FileProcessor.saveGalleryToFile(gallery)
    }


    /**
     * Compress images using @akrck02's image-compressor golang script
     */
    fun compress(path: String) {

        // Check if the file exists
        val currentFile = File(path)
        if (currentFile.exists().not())
            return

        // Execute the compression command
        val currentDir = File("./")
        val newPath = Paths.getThumbnailsAbsolutePath(currentFile.path)
        try {
            print(currentDir.execute(COMPRESSOR_BINARY_PATH, path, newPath, "400", ""))
            println()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
