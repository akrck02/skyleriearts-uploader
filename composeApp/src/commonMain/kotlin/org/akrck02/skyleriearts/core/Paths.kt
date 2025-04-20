package org.akrck02.skyleriearts.core

import io.github.cdimascio.dotenv.Dotenv
import io.github.cdimascio.dotenv.dotenv

object Paths {

    val dotenv: Dotenv = dotenv()
    val basePath = dotenv["WEB_PATH"] ?: "."
    val galleryFilePath = "$basePath/resources/data/images.json"

    /**
     * Get resources path
     */
    fun getUploadsPath(path: String): String {
        return StringBuilder("resources/images/upload").append("/").append(path).toString()
    }


    /**
     * Get resources path
     */
    fun getUploadsAbsolutePath(path: String): String {
        return StringBuilder("$basePath/resources/images/upload").append("/").append(path).toString()
    }

    /**
     * Get thumbnail path
     */
    fun getThumbnailsPath(path: String): String {

        var newPath = path
        if (newPath.contains("/")) {
            newPath = path.substring(path.lastIndexOf("/"))
        }

        newPath = StringBuilder("resources/images/thumbnails").append("/").append(newPath).toString()
        return newPath.replaceFirst(".", "-min.")
    }

    /**
     * Get thumbnail path
     */
    fun getThumbnailsAbsolutePath(path: String): String {

        var newPath = path
        if (newPath.contains("/")) {
            newPath = path.substring(path.lastIndexOf("/"))
        }

        newPath = StringBuilder("$basePath/resources/images/thumbnails").append("/").append(newPath).toString()
        return newPath.replaceFirst(".", "-min.")
    }

}

