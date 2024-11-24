package org.akrck02.skyleriearts.core


/**
 * Get resources path
 */
fun getResourcesPath(path: String): String {
    return StringBuilder("resources").append("/").append(path).toString()
}

/**
 * Get thumbnail path
 */
fun getThumbnailPath(path: String): String {

    var newPath = path
    if (newPath.contains("/")) {
        newPath = path.substring(path.lastIndexOf("/"))
    }

    newPath = StringBuilder("thumbnails").append("/").append(newPath).toString()
    return newPath.replaceFirst(".", "-min.")
}
