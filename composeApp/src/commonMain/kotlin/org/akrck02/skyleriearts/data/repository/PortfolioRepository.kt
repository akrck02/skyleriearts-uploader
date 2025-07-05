package org.akrck02.skyleriearts.data.repository

import org.akrck02.skyleriearts.data.model.Image

interface PortfolioRepository {

    // region categories
    fun getCategories(): MutableSet<String>
    fun searchCategoriesByName(name: String): MutableSet<String>
    // endregion

    // region projects
    fun getProjects(): MutableSet<String>
    fun getProjectsByCategory(category: String): MutableSet<String>
    fun searchProjectsByName(name: String): MutableSet<String>
    // endregion

    // region images
    fun getImage(name: String): Image?
    fun getImages(): MutableSet<Image>
    fun getImagesByCategory(category: String): MutableSet<Image>
    fun getImagesByProject(category: String): MutableSet<Image>
    fun searchImagesByName(name: String): MutableSet<Image>
    // endregion

}