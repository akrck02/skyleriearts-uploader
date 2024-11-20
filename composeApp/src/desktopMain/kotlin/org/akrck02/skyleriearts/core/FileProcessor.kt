package org.akrck02.skyleriearts.core

import java.io.File

/**
 * Add the file to the queue if needed
 * if the file already exists, returns
 * the current file.
 */
fun addFileToResources(filepath: String): File? {

    // If it is directory return
    val file = File(filepath)
    if (file.isDirectory)
        return null

    // If extension is invalid return
    if (validExtensions.contains(file.extension).not())
        return null

    // if the file exists return the file
    val newFile = File(getResourcesPath(file.name))
    if (newFile.exists())
        return newFile

    // Copy the file and return
    return file.copyTo(newFile)

}