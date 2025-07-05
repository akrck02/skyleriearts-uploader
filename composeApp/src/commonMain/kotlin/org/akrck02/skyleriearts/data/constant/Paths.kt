package org.akrck02.skyleriearts.data.constant

import io.github.cdimascio.dotenv.Dotenv
import io.github.cdimascio.dotenv.dotenv

object Paths {

    val dotenv: Dotenv = dotenv()
    val basePath = dotenv["WEB_PATH"] ?: "."
    val uploadsPath = "$basePath/${dotenv["UPLOADS_PATH"]}"
    val galleryFilePath = "$basePath/resources/data/images.json"

    /**
     * Get resources path
     */
    fun getUploadsPath(path: String): String {
        return "resources/images/upload/$path"
    }

    /**
     * Get resources path
     */
    fun getUploadsAbsolutePath(path: String): String {
        return "$uploadsPath/$path"
    }

    /**
     * Get thumbnail path
     */
    fun getThumbnailsPath(path: String): String {
        return "resources/images/uploads/${path.removeLastSlash()}".replaceFirst(".", "-min.")
    }


    /**
     * Get thumbnail path
     */
    fun getThumbnailsAbsolutePath(path: String): String {
        return "$basePath/resources/images/upload/${path.removeLastSlash()}".replaceFirst(".", "-min.")
    }

    /**
     * Remove last slash from path
     */
    fun String.removeLastSlash(): String {
        return if (this.contains("/")) this.substring(this.lastIndexOf("/")) else this
    }


}