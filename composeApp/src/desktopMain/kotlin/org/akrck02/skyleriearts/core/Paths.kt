package org.akrck02.skyleriearts.core


fun getResourcesPath(path: String): String {
    return StringBuilder("resources").append("/").append(path).toString()
}