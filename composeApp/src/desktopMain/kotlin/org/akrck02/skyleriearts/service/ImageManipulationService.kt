package org.akrck02.skyleriearts.service

import org.akrck02.skyleriearts.command.execute
import org.akrck02.skyleriearts.data.constant.Paths
import java.io.File

object ImageManipulationService : IImageManipulationService {

    const val COMPRESSOR_BINARY_PATH: String = "./bin/image-compressor"

    /**
     * Compress images using @akrck02's image-compressor golang script
     */
    override fun compress(path: String) {

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