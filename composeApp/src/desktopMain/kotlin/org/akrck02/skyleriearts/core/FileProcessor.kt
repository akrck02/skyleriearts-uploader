package org.akrck02.skyleriearts.core

import java.io.File

/**
 * Add the file to the queue
 */
fun addFileToResources(filepath: String): File? {

    // If it is directory return
    val file = File(filepath)
    if (file.isDirectory)
        return null

    // If extension is invalid return
    if (validExtensions.contains(file.extension).not())
        return null

    // Copy the file and return
    return file.copyTo(File(file.name))
}