package org.akrck02.skyleriearts.service

import org.akrck02.skyleriearts.command.execute
import org.akrck02.skyleriearts.data.constant.Paths
import org.akrck02.skyleriearts.data.constant.validExtensions
import org.akrck02.skyleriearts.exception.ErrorCode
import org.akrck02.skyleriearts.exception.SoftwareException
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

    override fun getImageFile(path: String): File {

        // If it is directory return
        val file = File(path)
        if (file.isDirectory) throw SoftwareException(code = ErrorCode.FilePathIsDirectory, message = "Cannot delete: File $path is a directory.")

        // If extension is invalid return
        if (validExtensions.contains(file.extension).not()) throw SoftwareException(
            code = ErrorCode.InvalidFileExtension,
            message = "Cannot delete: File $path has an invalid extension (${file.extension})."
        )

        return file
    }

}