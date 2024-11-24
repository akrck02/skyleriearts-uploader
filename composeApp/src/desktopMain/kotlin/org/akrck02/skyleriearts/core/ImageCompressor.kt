package org.akrck02.skyleriearts.core

import java.io.File

const val compressorBinaryPath: String = "./bin/image-compressor"

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
    val newPath = getThumbnailPath(currentFile.path)
    try {
        print(currentDir.execute(compressorBinaryPath, path, newPath, "400", ""))
        println()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
