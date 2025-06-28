package org.akrck02.skyleriearts.service

import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.graphics.ImageBitmap
import org.akrck02.skyleriearts.data.model.Image
import org.akrck02.skyleriearts.exception.SoftwareException
import java.io.File

interface IFileService {

    /**
     * Add the file to the queue if needed
     * if the file already exists, returns
     * the current file.
     * @param path The file path
     * @return The new file
     */
    fun add(path: String): File

    /**
     * Remove a file
     * @param path The file path
     * @throws SoftwareException if an error occurs
     */
    fun remove(path: String)

    /**
     * Get current gallery from file,
     * create the file if it does not exist
     * @return The map of names and image data
     */
    fun getGallery(): SnapshotStateMap<String, Image>

    /**
     * Save the current galley data to a file
     * @param gallery The gallery data (image name -> image data)
     */
    fun saveGallery(gallery: Map<String, Image>)

    /**
     * Load an image from path
     * @param path The file path
     * @return The loaded image
     */
    fun loadImage(path: String): ImageBitmap

}