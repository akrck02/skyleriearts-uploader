package org.akrck02.skyleriearts.data.model

import kotlinx.serialization.Serializable

/**
 * This class represents the state of the portfolio.
 *
 * The portfolio stores the images as well as the
 * categories and projects belonging to them.
 */
@Serializable
class Portfolio {
    var categories: MutableMap<String, MutableSet<String>> = mutableMapOf()
    var images: MutableMap<String, Image> = mutableMapOf()
}