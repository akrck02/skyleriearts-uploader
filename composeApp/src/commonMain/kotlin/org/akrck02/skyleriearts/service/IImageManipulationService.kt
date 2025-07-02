package org.akrck02.skyleriearts.service

import java.io.File

interface IImageManipulationService {

    /**
     * Compress the given image from path
     */
    fun compress(path: String)

    /**
     * Get image file from path
     */
    fun getImageFile(path: String): File
}