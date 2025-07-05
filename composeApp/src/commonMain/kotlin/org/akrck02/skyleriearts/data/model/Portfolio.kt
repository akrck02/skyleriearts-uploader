package org.akrck02.skyleriearts.data.model

class Portfolio {

    var categories: MutableMap<String, MutableSet<String>> = mutableMapOf()
    var images: MutableMap<String, Image> = mutableMapOf()

}